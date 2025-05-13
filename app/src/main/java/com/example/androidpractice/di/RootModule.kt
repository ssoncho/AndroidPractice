package com.example.androidpractice.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.androidpractice.characterList.data.mapper.CharacterResponseToEntityMapper
import com.example.androidpractice.characterList.data.repository.CharactersRepository
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.viewModel.DetailsViewModel
import com.example.androidpractice.characterList.presentation.viewModel.ListViewModel
import com.example.androidpractice.favorite.viewModel.FavoriteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single {
        getDataStore(androidContext())
    }

    single<ICharactersRepository> { CharactersRepository(get(), get(), get()) }

    factory { CharacterResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { FavoriteViewModel(get()) }
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
