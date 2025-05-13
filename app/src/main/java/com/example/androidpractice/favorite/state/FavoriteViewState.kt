package com.example.androidpractice.favorite.state

import com.example.androidpractice.characterList.domain.entity.CharacterEntity

data class FavoriteViewState(
    val items: List<CharacterEntity> = emptyList()
)