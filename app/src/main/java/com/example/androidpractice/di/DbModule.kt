package com.example.androidpractice.di

import android.content.Context
import androidx.room.Room
import com.example.androidpractice.characterList.data.database.CharacterDatabase
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseBuilder.getInstance(get()) }
}

object DatabaseBuilder {
    private var INSTANCE: CharacterDatabase? = null

    fun getInstance(context: Context): CharacterDatabase {
        if (INSTANCE == null) {
            synchronized(CharacterDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            "characters"
        ).build()
}