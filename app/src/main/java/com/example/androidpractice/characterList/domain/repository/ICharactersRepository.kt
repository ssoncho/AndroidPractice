package com.example.androidpractice.characterList.domain.repository

import com.example.androidpractice.characterList.domain.entity.CharacterEntity

interface ICharactersRepository {
    suspend fun getList(q: String): List<CharacterEntity>
    suspend fun getBySlug(slug: String): CharacterEntity?
}