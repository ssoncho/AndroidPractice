package com.example.androidpractice.characterList.presentation.state

import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.entity.HouseType

interface CharactersListState {
    val items: List<CharacterEntity>
    val query: String
    val isEmpty: Boolean
    val isLoading: Boolean
    val error: String?
    val hasBadge: Boolean
    val showTypesDialog: Boolean
    val typesVariants: Set<HouseType>
    val selectedTypes: Set<HouseType>
}