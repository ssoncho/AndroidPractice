package com.example.androidpractice.characterList.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.example.androidpractice.R
import com.example.androidpractice.characterList.presentation.viewModel.ListViewModel
import com.example.androidpractice.ui.components.BadgedIcon
import com.example.androidpractice.ui.components.CharacterListItem
import com.example.androidpractice.ui.components.FullScreenLoading
import com.example.androidpractice.ui.components.FullScreenMessage
import com.example.androidpractice.ui.components.SearchBar
import com.example.androidpractice.ui.theme.Spacing
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
                Row(
                    Modifier.padding(Spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SearchBar(
                        modifier = Modifier.weight(1f),
                        query = state.query
                    ) { query -> viewModel.onQueryChanged(query) }
                    BadgedIcon(
                        modifier = Modifier.padding(Spacing.small),
                        hasBadge = state.hasBadge
                    ) { viewModel.onFiltersClicked() }
                }
            }
        ) {
            if (state.showTypesDialog) {
                SelectionDialog(
                    onDismissRequest = { viewModel.onSelectionDialogDismissed() },
                    onConfirmation = { viewModel.onFiltersConfirmed() },
                    title = "House",
                    variants = state.typesVariants,
                    selectedVariants = state.selectedTypes
                ) { variant, isSelected ->
                    viewModel.onSelectedVariantChanged(variant, isSelected)
                }
            }

            if (state.isLoading) {
                FullScreenLoading()
                return@Scaffold
            }

            state.error?.let {
                FullScreenMessage(msg = it)
                return@Scaffold
            }

            if (state.isEmpty) {
                FullScreenMessage(stringResource(R.string.search_not_found))
                return@Scaffold
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) { item ->
                    CharacterListItem(
                        item = item,
                        Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { viewModel.onItemClicked(item.slug) },
                                onDoubleTap = { viewModel.onItemDoubleClicked(item) }
                            )
                        }
                    )
                }
            }
        }
    }
}
