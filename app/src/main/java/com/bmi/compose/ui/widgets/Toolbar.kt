package com.bmi.compose.ui.widgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.accentColor
import com.bmi.compose.theme.backgroundColor
import com.bmi.compose.theme.purple500
import com.vhi.bmicomposeinnovation.R

@Composable
fun Toolbar(
    title: String,
    color: Color = Color.White,
    elevation: Dp,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    toolbarBackground: Color = accentColor
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = color
            )
        },
        modifier = Modifier.preferredHeight(80.dp),
        navigationIcon = navigationIcon,
        actions = actions,
        elevation = elevation,
        backgroundColor = toolbarBackground
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    AppTheme {
        Toolbar(
            title = stringResource(R.string.app_name),
            navigationIcon = {
                RoundIconButton(
                    vectorAsset = Icons.Outlined.Notifications,
                    onClick = { })
            },
            actions = {
                RoundIconButton(vectorAsset = Icons.Outlined.Person, onClick = { })
            }, elevation = 10.dp
        )
    }
}

//@Preview
//@Composable
//fun ToolBarGradient() {
//    Box(
//        modifier = Modifier
//            .preferredSize(500.dp, 500.dp)
//            .background(
//                HorizontalGradient(
//                    0.0f to Color.Red,
//                    0.5f to Color.Green,
//                    1.0f to Color.Blue,
//                    startX = 0.dp,
//                    endX = 500.dp
//                )
//            )
//    )
//}
