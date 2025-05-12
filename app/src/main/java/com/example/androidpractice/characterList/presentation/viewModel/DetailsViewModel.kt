package com.example.androidpractice.characterList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.state.CharacterDetailsState
import com.example.androidpractice.core.coroutinesUtils.launchLoadingAndError
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class DetailsViewModel(
    private val repository: ICharactersRepository,
    private val navigation: StackNavContainer,
    private val slug: String
) : ViewModel() {
    private val mutableState = MutableDetailsState()
    val viewState = mutableState as CharacterDetailsState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.character = repository.getBySlug(slug)
        }
    }

    fun back() {
        navigation.back()
    }

    private class MutableDetailsState : CharacterDetailsState {
        override var character: CharacterEntity? by mutableStateOf(null)
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}
