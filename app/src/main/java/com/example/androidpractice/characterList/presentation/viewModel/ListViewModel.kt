package com.example.androidpractice.characterList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
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
import java.time.Duration

@OptIn(FlowPreview::class)
class ListViewModel(
    private val repository: ICharactersRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableCharactersListState()
    val viewState = mutableState as CharactersListState

    private val textChangesFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadCharacters(it) }
        }
    }

    private fun loadCharacters(query: String) {
        mutableState.items = emptyList()
        mutableState.error = null

        if (query.length < MIN_QUERY_LENGTH_TO_SEARCH) return

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.items = repository.getList(query)
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(slug: String) {
        navigation.forward(CharacterScreen(characterSlug = slug))
    }

    private class MutableCharactersListState : CharactersListState {
        override var items: List<CharacterEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }

    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
    }
}
