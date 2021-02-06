package com.bmi.compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiObjects
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bmi.compose.Screen
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.NormalWeightColor
import com.bmi.compose.theme.accentColor
import com.bmi.compose.theme.foregroundColor
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.RoundedButton
import com.bmi.compose.ui.widgets.Toolbar
import com.bmi.compose.util.BmiCalculator.ResultViewState
import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import com.vhi.bmicomposeinnovation.R

@Composable
fun ResultScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    toolbar: @Composable () -> Unit,
    bmi: ResultViewState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = toolbar,
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
                    modifier = Modifier.padding(18.dp),
                    backgroundColor = accentColor
                ) {
                    Box(
                        modifier = Modifier
                            .background(NormalWeightColor, CircleShape)
                            .preferredSize(112.dp)
                            .padding(8.dp),
                        alignment = Alignment.Center
                    ) {
                        Image(
                            imageResource(id = R.drawable.correct_weight),
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.fillMaxSize().padding(8.dp)
                        )
                    }
                }
            }

            Card(
                elevation = 0.dp,
                backgroundColor = NormalWeightColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                BasicText(
                    text = annotatedString {
                        append(stringResource(R.string.your_bmi_prepend))
                        withStyle(
                            style = SpanStyle(
                                color = foregroundColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = TextUnit.Sp(32)
                            )
                        ) {
                            append(" " + viewState.result.bmiString)
                        }
                    },

                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black.copy(alpha = 0.6f),
                        fontSize = TextUnit.Sp(32)
                    )
                )
            }

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {

                BasicText(
                    text = stringResource(R.string.normal_result_maintain_healthy_message),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = foregroundColor,
                        fontSize = TextUnit.Sp(18),
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )

                RowAdvice(
                    resId = R.drawable.stay_active,
                    advice = stringResource(R.string.advice_stay_active)
                )
                RowAdvice(
                    resId = R.drawable.no_junk_food,
                    advice = stringResource(R.string.advice_healthy_diet)
                )
                RowAdvice(
                    resId = R.drawable.sleep_well,
                    advice = stringResource(R.string.advice_sufficient_sleep)
                )
            }

            // details button
            RoundedButton(
                text = stringResource(R.string.result_more_info),
                onClick = { navigateTo(Screen.Info(viewState.result)) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .preferredWidth(120.dp),
                backGroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black.copy(alpha = 0.8f)
            )
        }
    }
    is Underweight -> {
    }
    is Overweight -> {
    }
    is Preview -> {
    }
}

@Composable
fun RowAdvice(resId: Int, advice: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 24.dp, top = 6.dp, bottom = 6.dp)
    ) {
        Image(vectorResource(resId), contentScale = Fit)

        Text(modifier = Modifier.padding(start = 12.dp), text = advice)
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
    actions = { RoundIconButton(vectorAsset = Icons.Outlined.EmojiObjects, onClick = { navigateTo(Screen.Tips)}) }
)
