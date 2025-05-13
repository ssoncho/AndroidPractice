package com.example.androidpractice.favorite.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.favorite.state.FavoriteViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: ICharactersRepository
): ViewModel() {
    private var mutableState = MutableStateFlow(FavoriteViewState())
    val viewState = mutableState.asStateFlow()

    init {
        updateFavorites()
    }
    fun onUpdateClick() {
        updateFavorites()
    }

    private fun updateFavorites() {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}