package com.example.androidpractice.characterList.presentation.state

import com.example.androidpractice.characterList.domain.entity.CharacterEntity

interface CharacterDetailsState {
    val character: CharacterEntity?
}