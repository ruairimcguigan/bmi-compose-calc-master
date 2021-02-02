package com.bmi.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bmi.compose.util.BmiCalculator
import com.bmi.compose.util.BmiCalculator.ResultViewState

sealed class Screen {
    object Home : Screen()
    data class Info(val bmi: BmiCalculator) : Screen()
    data class Result(val bmi: ResultViewState) : Screen()
    object Tips : Screen()
}

object ComposeStatus {
    var currentScreen by mutableStateOf<Screen>(Screen.Home)
    var previousScreen by mutableStateOf<Screen?>(null)
}

fun navigateTo(destination: Screen) {
    ComposeStatus.currentScreen = destination
}
