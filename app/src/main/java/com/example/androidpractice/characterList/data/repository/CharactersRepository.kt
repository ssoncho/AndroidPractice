package com.example.androidpractice.characterList.data.repository

import com.example.androidpractice.characterList.data.api.CharacterApi
import com.example.androidpractice.characterList.data.database.CharacterDatabase
import com.example.androidpractice.characterList.data.entity.CharacterDbEntity
import com.example.androidpractice.characterList.data.mapper.CharacterResponseToEntityMapper
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.entity.HouseType
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepository(
    private val api: CharacterApi,
    private val mapper: CharacterResponseToEntityMapper,
    private val db: CharacterDatabase
): ICharactersRepository {
    override suspend fun getList(q: String, filterTypes: Set<HouseType>?) =
        withContext(Dispatchers.IO) {
            val response = api.getCharacters(
                search = q,
                houseType = filterTypes?.takeIf { filterTypes.isNotEmpty() }?.joinToString(",") { it.name }
            )
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

    override suspend fun saveFavorite(character: CharacterEntity) =
        withContext(Dispatchers.IO) {
            db.characterDao().insert(
                CharacterDbEntity(
                    slug = character.slug,
                    name = character.name,
                    house = character.house.name,
                    species = character.species,
                    image = character.image,
                    animagus = character.animagus,
                    boggart = character.boggart,
                    patronus = character.patronus,
                    bloodStatus = character.bloodStatus,
                    maritalStatus = character.maritalStatus,
                    born = character.born,
                    died = character.died,
                    gender = character.gender,
                    nationality = character.nationality
                )
            )
        }

    override suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.characterDao().getAll().map {
                CharacterEntity(
                    slug = it.slug,
                    animagus = it.animagus,
                    bloodStatus = it.bloodStatus,
                    boggart = it.boggart,
                    born = it.born,
                    died = it.died,
                    gender = it.gender,
                    house = HouseType.getByValue(it.house),
                    image = it.image,
                    maritalStatus = it.maritalStatus,
                    name = it.name.orEmpty(),
                    nationality = it.nationality,
                    patronus = it.patronus,
                    species = it.species
                )
            }
        }
}