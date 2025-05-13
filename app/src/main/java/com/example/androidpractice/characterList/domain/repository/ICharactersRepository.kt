package com.example.androidpractice.characterList.domain.repository

import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.entity.HouseType

interface ICharactersRepository {
    suspend fun getList(q: String, filterTypes: Set<HouseType>? = null): List<CharacterEntity>
    suspend fun getBySlug(slug: String): CharacterEntity?
    suspend fun saveFavorite(character: CharacterEntity)
    suspend fun getFavorites(): List<CharacterEntity>
}