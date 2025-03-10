package com.example.androidpractice.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.androidpractice.container.presentation.screens.MainTabs

@Composable
fun BottomBar(onTabClick: (Int) -> Unit, selectedTabPos: Int) {
    BottomAppBar {
        for ((pos, tab) in MainTabs.entries.withIndex()) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    onTabClick(pos)
                },
            ) {
                val contentColor = LocalContentColor.current
                val color by animateColorAsState(
                    contentColor.copy(
                        alpha = if (pos == selectedTabPos) contentColor.alpha else 0.5f
                    ), label = ""
                )
                Icon(
                    rememberVectorPainter(tab.icon),
                    tint = color,
                    contentDescription = tab.title
                )
            }
        }
    }
}