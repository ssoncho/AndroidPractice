package com.example.androidpractice.characterList.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androidpractice.characterList.domain.entity.HouseType
import com.example.androidpractice.ui.theme.Spacing

@Composable
fun SelectionDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    variants: Set<HouseType>,
    selectedVariants: Set<HouseType>,
    onVariantSelectedChanged: (HouseType, Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                variants.forEach { variant ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = variant in selectedVariants,
                            onCheckedChange = { onVariantSelectedChanged(variant, it) }
                        )
                        Text(text = stringResource(id = variant.stringRes))
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(Spacing.small),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(Spacing.small),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectionDialogPreview() {
    SelectionDialog(
        onDismissRequest = {},
        onConfirmation = {},
        title = "House",
        variants = setOf(HouseType.Gryffindor, HouseType.Hufflepuff, HouseType.Ravenclaw, HouseType.Slytherin),
        selectedVariants = setOf(HouseType.Ravenclaw),
        onVariantSelectedChanged = { _, _ -> }
    )
}