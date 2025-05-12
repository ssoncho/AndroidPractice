package com.example.androidpractice.characterList.data.api

import com.example.androidpractice.characterList.data.model.CharacterResponse
import com.example.androidpractice.characterList.data.model.CharactersSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("filter[name_cont]") search: String,
        @Query("page[number]") page: Int = 1
    ) : CharactersSearchResponse

    @GET("characters/{slug}")
    suspend fun getCharacter(
        @Path("slug") slug: String? = null
    ) : CharacterResponse
}