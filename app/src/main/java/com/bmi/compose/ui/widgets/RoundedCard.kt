package com.bmi.compose.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.color4
import com.bmi.compose.theme.color5
import com.bmi.compose.ui.textStyle

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    color: Color = color5,
    elevation: Dp = 4.dp,
    content: @Composable() () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = color,
        elevation = elevation
    ) {
        content()
    }
}

@Preview
@Composable
private fun RoundedCardPreview() {
    AppTheme {
        RoundedCard {
            Text(text = "RoundedCard", modifier = Modifier.padding(8.dp), style = textStyle)
        }
    }
}

