package com.example.androidpractice.di

import com.example.androidpractice.characterList.data.repository.CharactersRepository
import com.example.androidpractice.characterList.domain.repository.ICharactersRepository
import com.example.androidpractice.characterList.presentation.viewModel.DetailsViewModel
import com.example.androidpractice.characterList.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<ICharactersRepository> { CharactersRepository() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}