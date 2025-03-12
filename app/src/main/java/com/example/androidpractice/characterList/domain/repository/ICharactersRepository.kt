package com.example.androidpractice.characterList.domain.repository

import com.example.androidpractice.characterList.domain.entity.CharacterEntity

interface ICharactersRepository {
    fun getList(q: String = ""): List<CharacterEntity>
    fun getBySlug(slug: String): CharacterEntity?
}