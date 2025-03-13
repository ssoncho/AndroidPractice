package com.example.androidpractice.characterList.data.repository

import com.example.androidpractice.characterList.data.mock.CharactersData
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository

class CharactersRepository: ICharactersRepository {
    override fun getList(q: String): List<CharacterEntity> =
        CharactersData.characters.filter { it.name.contains(q, ignoreCase = true) }

    override fun getBySlug(slug: String): CharacterEntity? =
        CharactersData.characters.find { it.slug == slug }
}