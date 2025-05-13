package com.example.androidpractice.container.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidpractice.characterList.presentation.screens.ListScreen
import com.example.androidpractice.favorite.screens.FavoriteScreen
import com.example.androidpractice.profile.presentation.screens.ProfileScreen
import com.example.androidpractice.ui.components.TabsAppBar
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.animation.SlideTransition
import com.github.terrakok.modo.multiscreen.selectScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class MainTabScreen(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        ListScreen(),
        FavoriteScreen(),
        ProfileScreen(),
        selected = 0
    )
) : MultiScreen(navModel) {
    @Composable
    override fun Content(modifier: Modifier) {
        MainTabContent(
            modifier = modifier,
            selectedTabPos = navigationState.selected,
            onTabClick = { pos ->
                selectScreen(pos)
            }
        ) {
            SelectedScreen(
                Modifier
                    .padding(this)
                    .fillMaxSize()
            ) { innerModifier ->
                SlideTransition(innerModifier)
            }
        }
    }
}

@Composable
fun MainTabContent(
    selectedTabPos: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable PaddingValues.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            TabsAppBar(onTabClick, selectedTabPos)
        }
    ) { paddingValues ->
        paddingValues.content()
    }
}

enum class MainTabs(
    val icon: ImageVector,
    val title: String
) {
    LIST(Icons.AutoMirrored.Rounded.List, "List"),
    FAVORITE(Icons.Default.Favorite, "Favorite"),
    PROFILE(Icons.Default.Face, "Profile")
}