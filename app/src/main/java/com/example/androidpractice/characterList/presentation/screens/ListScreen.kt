package com.example.androidpractice.characterList.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidpractice.characterList.presentation.state.CharactersListState
import com.example.androidpractice.characterList.presentation.viewModel.ListViewModel
import com.example.androidpractice.ui.components.CharacterListItem
import com.example.androidpractice.ui.components.EmptyDataBox
import com.example.androidpractice.ui.components.SearchBar
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                SearchBar(state.query) { query -> viewModel.onQueryChanged(query) }
            }
        ) {
            if (state.isEmpty) {
                EmptyDataBox("No results")
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) {
                    CharacterListItem(
                        item = it,
                        Modifier.clickable { viewModel.onItemClicked(it.slug) }
                    )
                }
            }
        }
    }
}