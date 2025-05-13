package com.example.androidpractice.characterList.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractice.characterList.data.entity.CharacterDbEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterDbEntity")
    suspend fun getAll(): List<CharacterDbEntity>

    @Insert
    suspend fun insert(driverDbEntity: CharacterDbEntity)
}