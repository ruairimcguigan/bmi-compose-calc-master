package com.bmi.compose.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.accentColor

private val IconButtonSizeModifier = Modifier.preferredSize(40.dp)

@Composable
fun RoundIconButton(
    vectorAsset: VectorAsset,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(8.dp),
    tint: Color = accentColor,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .then(IconButtonSizeModifier),
        shape = CircleShape,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {

        Icon(vectorAsset, tint = tint)
    }

}

@Preview
@Composable
private fun RoundIconPreview() {
    AppTheme {
        RoundIconButton(
            vectorAsset = Icons.Outlined.Notifications,
            onClick = {}
        )
    }
}