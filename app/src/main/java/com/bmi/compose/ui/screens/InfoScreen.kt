package com.bmi.compose.ui.screens

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.ui.widgets.EmptyHeight2
import com.bmi.compose.ui.widgets.Toolbar
import com.bmi.compose.Screen
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.accentColor
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.RoundedCard
import com.bmi.compose.util.BmiCalculator
import com.bmi.compose.util.BmiCalculator.ResultViewState
import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import com.vhi.bmicomposeinnovation.R

val textStyle = TextStyle(
    fontSize = TextUnit.Sp(16),
    color = Color.Black.copy(alpha = 0.8f)
)

@Composable
fun InfoScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    bmi: ResultViewState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(
                title = stringResource(R.string.bmi_info),
                navigationIcon = {
                    RoundIconButton(
                        vectorAsset = Icons.Outlined.ArrowBack,
                        onClick = { navigateTo(Screen.Result(bmi)) }
                    )
                },
                actions = { RoundIconButton(vectorAsset = Icons.Outlined.Person, onClick = { }) },
                elevation = 10.dp
            )
        },
        bodyContent = { Content(bmi) }
    )
}

@Composable
private fun Content(bmiState: ResultViewState) {

    when (bmiState) {
        is ResultViewState.Preview -> {
        }

        is Underweight -> {


            ScrollableColumn(
                modifier = Modifier.padding(12.dp).fillMaxSize()
            ) {
                RoundedCard(modifier = Modifier.padding(4.dp)) {
                    Row(
                        modifier = Modifier.padding(24.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Your BMI",
                            style = textStyle.copy(fontSize = TextUnit.Sp(18))
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = TextUnit.Sp(32)
                            )
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                color = accentColor,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

        }

        is NormalWeight -> {

            ScrollableColumn(
                modifier = Modifier.padding(12.dp).fillMaxSize()
            ) {
                RoundedCard(modifier = Modifier.padding(4.dp)) {
                    Row(
                        modifier = Modifier.padding(24.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Your BMI",
                            style = textStyle.copy(fontSize = TextUnit.Sp(18))
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = TextUnit.Sp(32)
                            )
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                color = accentColor,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

        }

        is Overweight -> {

            ScrollableColumn(
                modifier = Modifier.padding(12.dp).fillMaxSize()
            ) {
                RoundedCard(modifier = Modifier.padding(4.dp)) {
                    Row(
                        modifier = Modifier.padding(24.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Your BMI",
                            style = textStyle.copy(fontSize = TextUnit.Sp(18))
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = TextUnit.Sp(32)
                            )
                        )
                        Text(
                            text = bmiState.result.bmiString,
                            style = textStyle.copy(
                                color = accentColor,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

        }
    }


    EmptyHeight2()
    val infoList = listOf(
        "Less than 18.5" to "Underweight",
        "18.5 to 24.9" to "Normal",
        "25 to 29.9" to "Overweight",
        "More than 29.9" to "Obesity"
    )
    RoundedCard(modifier = Modifier.padding(4.dp)) {
        Column {
            infoList.fastForEachIndexed { index, pair ->
                InfoItemView(
                    info = pair,
                    hasDivider = index != infoList.lastIndex
                )
            }
        }
    }
}

@Composable()
private fun InfoItemView(info: Pair<String, String>, hasDivider: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = info.first,
            modifier = Modifier.weight(1f),
            style = textStyle
        )
        Text(
            text = info.second,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = textStyle.copy(fontWeight = FontWeight.Bold)
        )
    }
    if (hasDivider) {
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    AppTheme {
        InfoScreen(bmi = Preview(BmiCalculator(height = 202, weight = 62)))
    }
}
