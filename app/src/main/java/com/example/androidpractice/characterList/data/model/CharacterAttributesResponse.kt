package com.example.androidpractice.characterList.data.model

import com.google.gson.annotations.SerializedName

data class CharacterAttributesResponse(
    @SerializedName("slug")
    val slug: String?,

    @SerializedName("animagus")
    val animagus: String?,

    @SerializedName("blood_status")
    val bloodStatus: String?,

    @SerializedName("boggart")
    val boggart: String?,

    @SerializedName("born")
    val born: String?,

    @SerializedName("died")
    val died: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("house")
    val house: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("marital_status")
    val maritalStatus: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("nationality")
    val nationality: String?,

    @SerializedName("patronus")
    val patronus: String?,

    @SerializedName("species")
    val species: String?,
)