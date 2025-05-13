package com.example.androidpractice.characterList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.entity.HouseType
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.screens.CharacterScreen
import com.example.androidpractice.characterList.presentation.state.CharactersListState
import com.example.androidpractice.core.coroutinesUtils.launchLoadingAndError
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import org.koin.java.KoinJavaComponent.inject
import java.time.Duration

@OptIn(FlowPreview::class)
class ListViewModel(
    private val repository: ICharactersRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val dataStore: DataStore<Preferences> by inject(DataStore::class.java)
    private val typesKey = stringSetPreferencesKey(KEY_HOUSE_TYPES)

    private val mutableState = MutableCharactersListState()
    val viewState = mutableState as CharactersListState

    private val textChangesFlow = MutableStateFlow("")

    private var filterTypes: Set<HouseType> = emptySet()

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadCharacters() }
        }

        viewModelScope.launch {
            dataStore.data.collect {
                filterTypes = it[typesKey]
                    ?.map { HouseType.getByValue(it) }
                    ?.toSet()
                    .orEmpty()
                updateBadge()
            }
        }

        mutableState.typesVariants =
            setOf(HouseType.Gryffindor, HouseType.Hufflepuff, HouseType.Ravenclaw, HouseType.Slytherin)
    }

    private fun loadCharacters() {
        val query = textChangesFlow.value

        mutableState.items = emptyList()
        mutableState.error = null

        if (query.length < MIN_QUERY_LENGTH_TO_SEARCH) return

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.items = repository.getList(query, filterTypes)
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(slug: String) {
        navigation.forward(CharacterScreen(characterSlug = slug))
    }

    fun onFiltersClicked() {
        mutableState.showTypesDialog = true
        mutableState.selectedTypes = filterTypes
    }

    fun onSelectionDialogDismissed() {
        mutableState.showTypesDialog = false
    }

    fun onFiltersConfirmed() {
        if (filterTypes != mutableState.selectedTypes) {
            filterTypes = mutableState.selectedTypes
            loadCharacters()
            updateBadge()

            viewModelScope.launch {
                dataStore.edit {
                    it[typesKey] = filterTypes.map { it.name }.toSet()
                }
            }
        }
        onSelectionDialogDismissed()
    }

    fun onSelectedVariantChanged(variant: HouseType, selected: Boolean) {
        mutableState.selectedTypes = mutableState.selectedTypes.run {
            if (selected) plus(variant) else minus(variant)
        }
    }

    private fun updateBadge() {
        mutableState.hasBadge = filterTypes.isNotEmpty()
    }

    fun onItemDoubleClicked(item: CharacterEntity) {
        viewModelScope.launch {
            repository.saveFavorite(item)
        }
    }

    private class MutableCharactersListState : CharactersListState {
        override var items: List<CharacterEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var showTypesDialog: Boolean by mutableStateOf(false)
        override var typesVariants: Set<HouseType> by mutableStateOf(emptySet())
        override var selectedTypes: Set<HouseType> by mutableStateOf(emptySet())
        override var hasBadge: Boolean by mutableStateOf(false)
    }

    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
        private const val KEY_HOUSE_TYPES = "HOUSE_TYPES"
    }
}
