package com.example.androidpractice.characterList.data.repository

import android.util.Log
import com.example.androidpractice.characterList.data.api.CharacterApi
import com.example.androidpractice.characterList.data.mapper.CharacterResponseToEntityMapper
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepository(
    private val api: CharacterApi,
    private val mapper: CharacterResponseToEntityMapper
): ICharactersRepository {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getCharacters(q)
            mapper.mapSearch(response)
        }

    override suspend fun getBySlug(slug: String) =
        withContext(Dispatchers.IO) {
            val response = api.getCharacter(slug)
            if (response.errors != null) {
                throw Exception(response.errors[0].detail.orEmpty())
            }
            mapper.mapCharacter(response)
        }
}