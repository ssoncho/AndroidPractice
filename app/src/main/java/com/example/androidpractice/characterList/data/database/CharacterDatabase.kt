package com.example.androidpractice.characterList.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpractice.characterList.data.dao.CharacterDao
import com.example.androidpractice.characterList.data.entity.CharacterDbEntity

@Database(entities = [CharacterDbEntity::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}