package com.bmi.compose.ui.screens

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiObjects
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bmi.compose.Screen
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.*
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.RoundedButton
import com.bmi.compose.ui.widgets.Toolbar
import com.bmi.compose.util.BmiCalculator
import com.bmi.compose.util.BmiCalculator.ResultViewState
import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import com.vhi.bmicomposeinnovation.R
import com.vhi.bmicomposeinnovation.R.string.*

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

    is Underweight -> BMIResult(
        resultBackgroundColor = UnderWeightColor,
        resultTitle = result_title,
        resultSubtitle = underweight_result_maintain_subtitle,
        adviceOne = Pair(underweight_result_advice_one_message, Icons.Outlined.Info),
        adviceTwo = Pair(underweight_result_advice_two_message, Icons.Outlined.Info),
        adviceThree = Pair(underweight_result_advice_three_message, Icons.Outlined.Info),
        viewState = viewState
    )

    is NormalWeight -> BMIResult(
        resultBackgroundColor = NormalWeightColor,
        resultTitle = result_title,
        resultSubtitle = normal_result_maintain_subtitle,
        adviceOne = Pair(normal_result_advice_one_message, Icons.Outlined.Info),
        adviceTwo = Pair(normal_result_advice_two_message, Icons.Outlined.Info),
        adviceThree = Pair(normal_result_advice_three_message, Icons.Outlined.Info),
        viewState = viewState
    )

    is Overweight -> BMIResult(
        resultBackgroundColor = Red900,
        resultTitle = result_title,
        resultSubtitle = overweight_result_maintain_subtitle,
        adviceOne = Pair(overweight_result_advice_one_message, Icons.Outlined.Info),
        adviceTwo = Pair(overweight_result_advice_two_message, Icons.Outlined.Info),
        adviceThree = Pair(overweight_result_advice_three_message, Icons.Outlined.Info),
        viewState = viewState
    )
    is Preview -> { }
}

@Composable
private fun BMIResult(
    @ColorRes resultBackgroundColor: Color,
    @StringRes resultTitle: Int,
    @StringRes resultSubtitle: Int = 0,
    adviceOne: Pair<Int, VectorAsset>,
    adviceTwo: Pair<Int, VectorAsset>,
    adviceThree: Pair<Int, VectorAsset>,
    viewState: ResultViewState
) {

    val bmiValueState: BmiCalculator  = when(viewState){
        is NormalWeight -> viewState.result
        is Preview -> viewState.result
        is Underweight -> viewState.result
        is Overweight -> viewState.result
    }

    Column(
        modifier = Modifier.fillMaxSize().background(resultBackgroundColor),
        verticalArrangement = SpaceAround
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
                        .background(resultBackgroundColor, CircleShape)
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
            backgroundColor = resultBackgroundColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            BasicText(
                text = annotatedString {
                    append(stringResource(your_bmi_prepend))
                    withStyle(
                        style = SpanStyle(
                            color = foregroundColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit.Sp(32)
                        )
                    ) {
                        append(" ${bmiValueState.bmiString}")
                    }
                },

                style = TextStyle(
                    fontWeight = SemiBold,
                    color = Color.Black.copy(alpha = 0.6f),
                    fontSize = TextUnit.Sp(32)
                )
            )
        }

        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            BasicText(
                text = stringResource( resultTitle),
                style = TextStyle(
                    fontWeight = Medium,
                    color = foregroundColor,
                    fontSize = TextUnit.Sp(22),
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )

            BasicText(
                text = stringResource(resultSubtitle),
                style = TextStyle(
                    fontWeight = Medium,
                    color = foregroundColor,
                    fontSize = TextUnit.Sp(22),
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )

            RowAdvice(advice = adviceOne.first, icon = adviceOne.second)
            RowAdvice(advice = adviceTwo.first, icon = adviceTwo.second)
            RowAdvice(advice = adviceThree.first, icon = adviceThree.second)
        }

        // details button
        RoundedButton(
            text = stringResource(R.string.result_more_info),
            onClick = { navigateTo(Screen.Info(bmiValueState)) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .preferredWidth(120.dp),
            backGroundColor = MaterialTheme.colors.background,
            contentColor = Color.Black.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun RowAdvice(icon: VectorAsset, advice: Int) {
    Row(
        horizontalArrangement = SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 24.dp, top = 6.dp, bottom = 6.dp)
    ) {
        Image(icon, contentScale = Fit)

        Text(modifier = Modifier.padding(start = 12.dp),
            text = stringResource(advice),
            fontSize = TextUnit.Sp(16)
        )
    }
}

@Composable
fun VhiToolbar(
    title: String = "",
    color: Color = Color.Black,
    elevation: Dp,
    navigationIcon: @Composable (() -> Unit)? = null
) = Toolbar(
    title = title,
    color = Color.White,
    elevation = elevation,
    toolbarBackground = color,
    navigationIcon = navigationIcon,
    actions = { RoundIconButton(vectorAsset = Icons.Outlined.EmojiObjects, onClick = { navigateTo(Screen.Tips)}) }
)
