package com.example.androidpractice.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidpractice.R
import com.example.androidpractice.ui.theme.Spacing

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        label = { Text(stringResource(R.string.search_bar_title)) },
        modifier = modifier,
        leadingIcon = { Icon(Icons.Rounded.Search, null) }
    )
}