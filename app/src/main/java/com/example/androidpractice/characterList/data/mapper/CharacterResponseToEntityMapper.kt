package com.example.androidpractice.characterList.data.mapper

import com.example.androidpractice.characterList.data.model.CharacterResponse
import com.example.androidpractice.characterList.data.model.CharactersSearchResponse
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.characterList.domain.entity.HouseType

class CharacterResponseToEntityMapper {
    fun mapSearch(response: CharactersSearchResponse) =
        response.data?.map { character ->
                val characterAttributes = character.attributes
                CharacterEntity(
                    slug = characterAttributes.slug.orEmpty(),
                    animagus = characterAttributes.animagus,
                    bloodStatus = characterAttributes.bloodStatus,
                    boggart = characterAttributes.boggart,
                    born = characterAttributes.born,
                    died = characterAttributes.died,
                    gender = characterAttributes.gender,
                    house = HouseType.getByValue(characterAttributes.house),
                    image = characterAttributes.image,
                    maritalStatus = characterAttributes.maritalStatus,
                    name = characterAttributes.name.orEmpty(),
                    nationality = characterAttributes.nationality,
                    patronus = characterAttributes.patronus,
                    species = characterAttributes.species
                )
        }.orEmpty()

    fun mapCharacter(response: CharacterResponse): CharacterEntity {
        val characterAttributes = response.data?.attributes
        return CharacterEntity(
            slug = characterAttributes?.slug.orEmpty(),
            animagus = characterAttributes?.animagus,
            bloodStatus = characterAttributes?.bloodStatus,
            boggart = characterAttributes?.boggart,
            born = characterAttributes?.born,
            died = characterAttributes?.died,
            gender = characterAttributes?.gender,
            house = HouseType.getByValue(characterAttributes?.house),
            image = characterAttributes?.image,
            maritalStatus = characterAttributes?.maritalStatus,
            name = characterAttributes?.name.orEmpty(),
            nationality = characterAttributes?.nationality,
            patronus = characterAttributes?.patronus,
            species = characterAttributes?.species
        )
    }
}