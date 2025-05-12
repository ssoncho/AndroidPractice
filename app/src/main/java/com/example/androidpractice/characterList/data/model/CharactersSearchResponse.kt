package com.example.androidpractice.characterList.data.model

import com.google.gson.annotations.SerializedName

data class CharactersSearchResponse(
    @SerializedName("data")
    val data: List<CharacterDataResponse>?
)