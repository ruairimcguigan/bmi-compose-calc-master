package com.bmi.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.NormalWeightColor
import com.bmi.compose.ui.screens.HomeScreen
import com.bmi.compose.ui.screens.InfoScreen
import com.bmi.compose.ui.screens.ResultScreen
import com.bmi.compose.ui.screens.TipsScreen
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.Toolbar
import com.vhi.bmicomposeinnovation.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ComposeApp()
            }
        }
    }

    override fun onBackPressed() {
        if (ComposeStatus.currentScreen == Screen.Home) {
            super.onBackPressed()
        }
    }
}

@Composable
fun ComposeApp() {
    AppTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {
    Crossfade(ComposeStatus.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Home -> HomeScreen()
                is Screen.Info -> InfoScreen(bmi = screen.bmi.result)
                is Screen.Result -> ResultScreen(
                    bmi = screen.bmi,
                    toolbar = {
                        ResultToolbar(
                            title = stringResource(R.string.normal_top_bar_result),
                            color = NormalWeightColor

                        )
                    }
                )
                is Screen.Tips -> TipsScreen()
            }
        }
    }
}

@Composable
fun ResultToolbar(
    title: String = "",
    color: Color = Color.Black,
    navigationIcon: @Composable (() -> Unit)? = null
) = Toolbar(
    title = title,
    color = Color.White,
    elevation = 0.dp,
    toolbarBackground = color,
    navigationIcon = navigationIcon,
    actions = { RoundIconButton(vectorAsset = Icons.Outlined.Person, onClick = { }) }
)




