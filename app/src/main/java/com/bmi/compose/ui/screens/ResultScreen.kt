package com.bmi.compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.ui.widgets.Toolbar
import com.bmi.compose.Screen
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.NormalWeightColor
import com.bmi.compose.theme.accentColor
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.RoundedButton
import com.bmi.compose.util.BmiCalculator
import com.bmi.compose.util.BmiCalculator.ResultViewState
import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import com.vhi.bmicomposeinnovation.R

@Composable
fun ResultScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    bmi: ResultViewState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(
                title = stringResource(R.string.bmi_results),
                navigationIcon = { RoundIconButton(vectorAsset = Icons.Outlined.ArrowBack, onClick = { navigateTo(Screen.Home) }) },
                actions = { RoundIconButton(vectorAsset = Icons.Outlined.Person, onClick = { }) }) },
        bodyContent = { Content(bmi) }
    )
}

@Composable
private fun Content(viewState: ResultViewState) = when (viewState) {
    is NormalWeight -> {

        Column(
            modifier = Modifier.fillMaxSize().background(NormalWeightColor),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Card(
                shape = CircleShape,
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier.padding(32.dp),
                    backgroundColor = accentColor
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background, CircleShape)
                            .preferredSize(112.dp)
                            .padding(8.dp),
                        alignment = Alignment.Center
                    ) {
                        BasicText(
                            text = viewState.result.bmiString,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black.copy(alpha = 0.8f),
                                fontSize = TextUnit.Sp(32)
                            )
                        )
                    }
                }
            }
            // "You have 21.5 body weight"
            Text(
                style = textStyle.copy(
                    fontSize = TextUnit.Sp(18)
                ),
                text = annotatedString {
                    append("You have ")
                    withStyle(
                        style = SpanStyle(color = accentColor, fontWeight = FontWeight.Bold)
                    ) {
                        append(viewState.result.bmiString)
                    }
                    append(" body weight!")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            RoundedButton(
                text = "Details",
                onClick = { navigateTo(Screen.Info(viewState.result)) },
                modifier = Modifier.align(Alignment.CenterHorizontally).preferredWidth(120.dp),
                backGroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black.copy(alpha = 0.8f)
            )
        }
    }
    is Underweight -> { }
    is Overweight -> { }
    is ResultViewState.Preview -> { }
}

@Preview
@Composable
private fun ScreenPreview() {
    AppTheme {
        ResultScreen(bmi = Preview(BmiCalculator(height = 202, weight = 62)))
    }
}
