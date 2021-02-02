package com.bmi.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.ui.screens.HomeScreen
import com.bmi.compose.ui.screens.InfoScreen
import com.bmi.compose.ui.screens.ResultScreen
import com.bmi.compose.ui.screens.TipsScreen

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
                is Screen.Result -> ResultScreen(bmi = screen.bmi)
                is Screen.Tips -> TipsScreen()
            }
        }
    }
}