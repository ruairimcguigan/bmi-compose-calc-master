package com.bmi.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bmi.compose.Screen.*
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.NormalWeightColor
import com.bmi.compose.theme.Red900
import com.bmi.compose.theme.UnderWeightColor
import com.bmi.compose.ui.screens.*
import com.bmi.compose.ui.widgets.RoundIconButton
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
        if (ComposeStatus.currentScreen == Home) {
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
                is Home -> HomeScreen()
                is Info -> InfoScreen(bmi = screen.bmi.result)
                is UnderweightScreen -> ResultScreen(
                        bmi = screen.bmi,
                        toolbar = {
                            VhiToolbar(
                                title = stringResource(R.string.underweight_top_bar_result),
                                color = UnderWeightColor,
                                navigationIcon = {
                                    RoundIconButton(
                                        vectorAsset = Icons.Outlined.ArrowBack,
                                        onClick = { navigateTo(Home) }
                                    )
                                },
                                elevation = 0.dp
                            )
                        }
                    )
                is NormalScreen -> ResultScreen(
                    bmi = screen.bmi,
                    toolbar = {
                        VhiToolbar(
                            title = stringResource(R.string.normal_top_bar_result),
                            color = NormalWeightColor,
                            navigationIcon = {
                                RoundIconButton(
                                    vectorAsset = Icons.Outlined.ArrowBack,
                                    onClick = { navigateTo(Home) }
                                )
                            },
                            elevation = 0.dp
                        )
                    }
                )
                is OverweightScreen -> ResultScreen(
                    bmi = screen.bmi,
                    toolbar = {
                        VhiToolbar(
                            title = stringResource(R.string.overweight_top_bar_result),
                            color = Red900,
                            navigationIcon = {
                                RoundIconButton(
                                    vectorAsset = Icons.Outlined.ArrowBack,
                                    onClick = { navigateTo(Home) }
                                )
                            },
                            elevation = 0.dp
                        )
                    }
                )
                is Tips -> TipsScreen()
            }
        }
    }
}




