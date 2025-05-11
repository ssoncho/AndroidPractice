package com.example.androidpractice.characterList.data.model

import com.google.gson.annotations.SerializedName

data class CharacterDataResponse(
    @SerializedName("attributes")
    val attributes: CharacterAttributesResponse
)