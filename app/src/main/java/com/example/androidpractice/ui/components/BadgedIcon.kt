package com.example.androidpractice.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BadgedIcon(
    modifier: Modifier = Modifier,
    hasBadge: Boolean = false,
    onClicked: () -> Unit
){
    BadgedBox(
        badge = { if (hasBadge) Badge() },
        modifier
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "More",
            modifier = Modifier.clickable { onClicked() }
        )
    }
}