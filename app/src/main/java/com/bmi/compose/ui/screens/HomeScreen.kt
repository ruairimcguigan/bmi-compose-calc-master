@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.bmi.compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.Screen
import com.bmi.compose.Screen.*
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.accentColor
import com.bmi.compose.ui.screens.PersonalDetails.Age
import com.bmi.compose.ui.screens.PersonalDetails.Weight
import com.bmi.compose.ui.widgets.*
import com.bmi.compose.util.BmiCalculator
import com.bmi.compose.util.BmiCalculator.ResultViewState.*
import com.vhi.bmicomposeinnovation.R
import com.vhi.bmicomposeinnovation.R.string.unit_age
import com.vhi.bmicomposeinnovation.R.string.unit_weight

@Composable
fun HomeScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
        VhiToolbar(
            title = stringResource(R.string.home_title),
            color = accentColor,
            elevation = 10.dp,


        )
    },
    bodyContent = { Content() }
)

@Composable
private fun Content() = Column(
    modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
    verticalArrangement = SpaceAround
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = SpaceEvenly
    ) {

        val maleState = mutableStateOf(true)
        val femaleState = mutableStateOf(false)

        RoundedToggleButton(
            state = maleState,
            text = stringResource(id = R.string.male),
            onClick = {
                maleState.value = true
                femaleState.value = false
            },
            modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f)
        )
        RoundedToggleButton(
            state = femaleState,
            text = stringResource(id = R.string.female),
            onClick = {
                femaleState.value = true
                maleState.value = false
            },
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp)
                .weight(1f)
        )
    }

    val heightState = remember { mutableStateOf(170) }
    val weightState: MutableState<Int> = remember { mutableStateOf(62) }
    val ageState: MutableState<Int> = remember { mutableStateOf(20) }

    PickerView(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(top = 4.dp),
        heightState = heightState,
        weightState = weightState,
        ageState = ageState
    )

    RoundedButton(
        text = stringResource(id = R.string.submit_bmi),
        onClick = {
            val bmi = BmiCalculator(
                heightState.value,
                weightState.value
            )

            when(bmi.result){
                is Underweight -> navigateTo(UnderweightScreen(bmi.result))
                is NormalWeight -> navigateTo(NormalScreen(bmi.result))
                is Overweight -> navigateTo(OverweightScreen(bmi.result))
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    )
}

@Composable
private fun PickerView(
    modifier: Modifier = Modifier,
    heightState: MutableState<Int>,
    weightState: MutableState<Int>,
    ageState: MutableState<Int>
) = Column(
    modifier = modifier,
    verticalArrangement = SpaceEvenly
) {

    HeightSelector(
        modifier = Modifier
            .weight(1f)
            .align(CenterHorizontally)
            .padding(start = 2.dp, bottom = 8.dp, end = 2.dp)
            .fillMaxHeight(), heightState = heightState
    )
    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(top = 8.dp),
        horizontalArrangement = SpaceEvenly
    ) {

        NumberPicker(
            type = Weight,
            label = stringResource(id = R.string.lbl_weight),
            modifier = Modifier
                .weight(1f)
                .padding(start = 2.dp, end = 8.dp)
                .fillMaxHeight(),
            pickerState = weightState
        )
        NumberPicker(
            type = Age,
            label = stringResource(id = R.string.lbl_age),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 2.dp)
                .fillMaxHeight(),
            pickerState = ageState
        )
    }
}

@Composable
private fun HeightSelector(
    modifier: Modifier = Modifier,
    heightState: MutableState<Int>
) {
    val height = annotatedString {
        withStyle(
            style = SpanStyle(fontSize = Sp(32))
        ) { append(heightState.value.toString()) }
        append(stringResource(id = R.string.unit_cm))
    }

    RoundedCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Center
        ) {

            Text(
                text = stringResource(id = R.string.lbl_height),
                modifier = Modifier.align(CenterHorizontally),
                style = LabelStyle
            )
            Slider(
                value = heightState.value.toFloat(),
                onValueChange = { heightState.value = it.toInt() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                valueRange = (1f..272f),
                activeTrackColor = accentColor
            )
            Text(
                text = height,
                modifier = Modifier.align(CenterHorizontally),
                style = textStyle
            )
        }
    }
}

@Composable
private fun NumberPicker(
    type: PersonalDetails,
    label: String,
    modifier: Modifier = Modifier,
    pickerState: MutableState<Int>,
    range: IntRange = 1..100
) = RoundedCard(modifier = modifier) {

    val value = when(type){

        is Weight-> annotatedString {
            withStyle(
                style = SpanStyle(fontSize = Sp(32))
            ) { append(pickerState.value.toString()) }
            append(stringResource(unit_weight))
        }
        is Age -> annotatedString {
            withStyle(
                style = SpanStyle(fontSize = Sp(32))
            ) { append(pickerState.value.toString()) }
            append(stringResource(unit_age))
        }
    }

    Column(
        modifier = Modifier,
        verticalArrangement = Center
    ) {
        Text(
            text = label,
            style = LabelStyle,
            modifier = Modifier.align(CenterHorizontally)
        )
        Text(
            text = value,
            style = textStyle,
            modifier = Modifier.align(CenterHorizontally)
        )
        Row(
            horizontalArrangement = SpaceEvenly,
            modifier = Modifier.align(CenterHorizontally)
        ) {
            RoundIconButton(vectorAsset = Icons.Default.Add, onClick = {
                if (pickerState.value < range.last) {
                    pickerState.value = pickerState.value + 1
                }
            })
            RoundIconButton(vectorAsset = Icons.Default.Remove, onClick = {
                if (pickerState.value > range.first) {
                    pickerState.value = pickerState.value - 1
                }
            })
        }
    }
}

sealed class PersonalDetails {
    object Weight: PersonalDetails()
    object Age: PersonalDetails()
}

@Preview
@Composable
private fun ScreenPreview() = AppTheme { HomeScreen() }

private val LabelStyle = TextStyle(
    color = Color.Black.copy(alpha = 0.6f),
    fontSize = Sp(18)
)

private val ValueStyle = TextStyle(
    color = Color.Black.copy(alpha = 0.9f),
    fontSize = Sp(32)
)