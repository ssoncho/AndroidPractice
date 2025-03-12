package com.example.androidpractice.characterList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.state.CharacterDetailsState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class DetailsViewModel(
    private val repository: ICharactersRepository,
    private val navigation: StackNavContainer,
    private val slug: String
    ): ViewModel() {
    private val mutableState = MutableDetailsState()
    val viewState = mutableState as CharacterDetailsState

    init {
        mutableState.character = repository.getBySlug(slug)
    }

    fun back() {
        navigation.back()
    }

    private class MutableDetailsState : CharacterDetailsState {
        override var character: CharacterEntity? by mutableStateOf(null)
    }
}