package com.example.androidpractice.characterList.presentation.state

import com.example.androidpractice.characterList.domain.entity.CharacterEntity

interface CharactersListState {
    val items: List<CharacterEntity>
    val query: String
    val isEmpty: Boolean
}