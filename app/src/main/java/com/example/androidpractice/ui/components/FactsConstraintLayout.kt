package com.example.androidpractice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androidpractice.R
import com.example.androidpractice.ui.theme.Spacing

@Composable
fun FactsConstraintLayout(
    patronus: String?,
    boggart: String?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
){
    ConstraintLayout(
        modifier = modifier
    ) {
        val (label1, box1, label2, box2) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(label1) {
                    top.linkTo(parent.top, margin = 16.dp)
                },
            text = "Patronus:",
            style = textStyle
        )
        Box(
            modifier = Modifier
                .background(Color.Green, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = Spacing.medium, vertical = Spacing.small)
                .constrainAs(box1) {
                    top.linkTo(label1.top)
                    start.linkTo(label1.end, margin = 4.dp)
                    centerVerticallyTo(label1)
                }
        ) {
            Text(
                text = patronus ?: stringResource(R.string.unknown),
                color = Color.White,
                style = textStyle
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(label2) {
                    top.linkTo(box1.bottom, margin = 8.dp)
                    centerHorizontallyTo(box1)
                },
            text = "Boggart:",
            style = textStyle
        )
        Box(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = Spacing.medium, vertical = Spacing.small)
                .constrainAs(box2) {
                    top.linkTo(label2.top)
                    start.linkTo(label2.end, margin = 4.dp)
                }
        ) {
            Text(
                text = boggart ?: stringResource(R.string.unknown),
                color = Color.White,
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FactsConstraintLayoutPreview() = FactsConstraintLayout("Otter", "Failure")