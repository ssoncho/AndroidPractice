package com.example.androidpractice.characterList.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    val data: CharacterDataResponse?
): ApiBaseResponse()
