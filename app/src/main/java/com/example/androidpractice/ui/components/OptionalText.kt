package com.example.androidpractice.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun OptionalText(label: String, value: String?) {
    value?.let { v ->
        Text(
            text = "$label: $v",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}