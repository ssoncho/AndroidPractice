package com.example.androidpractice.characterList.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CharacterDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "house") val house: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "animagus") val animagus: String?,
    @ColumnInfo(name = "boggart") val boggart: String?,
    @ColumnInfo(name = "patronus") val patronus: String?,
    @ColumnInfo(name = "bloodStatus") val bloodStatus: String?,
    @ColumnInfo(name = "maritalStatus") val maritalStatus: String?,
    @ColumnInfo(name = "born") val born: String?,
    @ColumnInfo(name = "died") val died: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "nationality") val nationality: String?,
)
