package com.bmi.compose.util

import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

data class BmiCalculator(val height: Int, val weight: Int) {

    var bmi: Double = 0.0

    init {
        bmi = (weight / (height.toDouble() / 100).pow(2.0)).roundOff()
    }

    private fun Double.roundOff(): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(this).toDouble()
    }

    val bmiString: String
        get() = bmi.toString()

    val result: ResultViewState
        get() = when {
            bmi < 18.5 -> Underweight(this)
            bmi in (18.5..24.9) -> NormalWeight(this)
            else -> Overweight(this)
        }

    val bmiPercentage: Double
        get() {
            //Above 25 person is overweight so after 29.9 bmi percentage chart will be full 100%
            return bmi / 29.9 * 100
        }

    sealed class ResultViewState {
        data class Preview(val result: BmiCalculator): ResultViewState()
        data class Underweight(val result: BmiCalculator): ResultViewState()
        data class NormalWeight(val result: BmiCalculator): ResultViewState()
        data class Overweight(val result: BmiCalculator): ResultViewState()
    }
}
