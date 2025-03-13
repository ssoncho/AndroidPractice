package com.example.androidpractice.characterList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.screens.CharacterScreen
import com.github.terrakok.modo.stack.StackNavContainer
import com.example.androidpractice.characterList.presentation.state.CharactersListState
import com.github.terrakok.modo.stack.forward

class ListViewModel(
    private val repository: ICharactersRepository,
    private val navigation: StackNavContainer
): ViewModel() {
    private val mutableState = MutableCharactersListState()
    val viewState = mutableState as CharactersListState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadCharacters()
    }

    fun onItemClicked(slug: String) {
        navigation.forward(CharacterScreen(characterSlug = slug))
    }

    private class MutableCharactersListState: CharactersListState {
        override var items: List<CharacterEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
    }
}