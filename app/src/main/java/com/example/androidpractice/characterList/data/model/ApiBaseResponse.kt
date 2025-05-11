package com.example.androidpractice.characterList.data.model

import com.google.gson.annotations.SerializedName

abstract class ApiBaseResponse {
    @SerializedName("errors")
    val errors: List<Error>? = null
}

data class Error(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("detail")
    val detail: String? = null
)