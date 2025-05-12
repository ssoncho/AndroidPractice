package com.example.androidpractice.characterList.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidpractice.R
import com.example.androidpractice.characterList.presentation.viewModel.DetailsViewModel
import com.example.androidpractice.ui.components.BackAppBar
import com.example.androidpractice.ui.components.CharacterCard
import com.example.androidpractice.ui.components.FullScreenLoading
import com.example.androidpractice.ui.components.FullScreenMessage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class CharacterScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
    val characterSlug: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<DetailsViewModel> { parametersOf(navigation, characterSlug) }
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                BackAppBar(
                    stringResource(R.string.details_top_bar_title)) { viewModel.back() }
            }
        ) { paddingValues ->
            if (state.isLoading) {
                FullScreenLoading()
                return@Scaffold
            }

            state.error?.let {
                FullScreenMessage(msg = it)
                return@Scaffold
            }

            CharacterCard(
                modifier = Modifier.padding(paddingValues),
                state.character
            )
        }
    }
}