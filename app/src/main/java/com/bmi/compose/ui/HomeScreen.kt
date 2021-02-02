package com.bmi.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bmi.compose.ui.widgets.Toolbar
import com.bmi.compose.Screen
import com.bmi.compose.navigateTo
import com.bmi.compose.theme.AppTheme
import com.bmi.compose.theme.accentColor
import com.bmi.compose.theme.color4
import com.bmi.compose.ui.widgets.RoundIconButton
import com.bmi.compose.ui.widgets.RoundedButton
import com.bmi.compose.ui.widgets.RoundedCard
import com.bmi.compose.ui.widgets.RoundedToggleButton
import com.bmi.compose.util.BmiCalculator
import com.vhi.bmicomposeinnovation.R
import java.util.*

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.app_name),
                navigationIcon = {
                    RoundIconButton(
                        vectorAsset = Icons.Outlined.Notifications,
                        onClick = { navigateTo(Screen.Tips) }
                    )
                },
                actions = {
                    RoundIconButton(vectorAsset = Icons.Outlined.Person, onClick = { })
                }
            )
        },
        bodyContent = {
            Content()
        }
    )
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
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
                modifier = Modifier.padding(end = 8.dp).weight(1f)
            )
            RoundedToggleButton(
                state = femaleState,
                text = stringResource(id = R.string.female),
                onClick = {
                    femaleState.value = true
                    maleState.value = false
                },
                modifier = Modifier.padding(start = 8.dp).weight(1f)
            )
        }
        val heightState = remember { mutableStateOf(170) }
        val weightState: MutableState<Int> = remember { mutableStateOf(62) }
        val ageState: MutableState<Int> = remember { mutableStateOf(20) }

        PickerView(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 16.dp),
            heightState = heightState,
            weightState = weightState,
            ageState = ageState
        )

        RoundedButton(
            text = stringResource(id = R.string.begin),
            onClick = {
                val bmi = BmiCalculator(
                    heightState.value,
                    weightState.value
                )
                navigateTo(Screen.Result(bmi))
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
    }
}

@Composable
private fun PickerView(
    modifier: Modifier = Modifier,
    heightState: MutableState<Int>,
    weightState: MutableState<Int>,
    ageState: MutableState<Int>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        HeightSelector(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
                .fillMaxHeight(),
            heightState = heightState
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NumberPicker(
                label = stringResource(id = R.string.lbl_weight),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .fillMaxHeight(),
                pickerState = weightState
            )
            NumberPicker(
                label = stringResource(id = R.string.lbl_age),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxHeight(),
                pickerState = ageState
            )
        }
    }
}

@Composable
private fun HeightSelector(
    modifier: Modifier = Modifier,
    heightState: MutableState<Int>
) {
    val height = annotatedString {
        withStyle(
            style = SpanStyle(fontSize = TextUnit.Sp(32))
        ) { append(heightState.value.toString()) }
        append(stringResource(id = R.string.unit_cm))
    }

    RoundedCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.lbl_height),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = LabelStyle
            )
            Slider(
                value = heightState.value.toFloat(),
                onValueChange = { heightState.value = it.toInt() },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                valueRange = (1f..272f),
                activeTrackColor = accentColor
            )
            Text(
                text = height,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = textStyle
            )
        }
    }
}

@Composable
private fun NumberPicker(
    label: String,
    modifier: Modifier = Modifier,
    pickerState: MutableState<Int>,
    range: IntRange = 1..100
) {
    RoundedCard(modifier = modifier) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = LabelStyle,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = pickerState.value.toString(),
                style = ValueStyle,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.align(Alignment.CenterHorizontally)
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
}

@Preview
@Composable
private fun ScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}

private val LabelStyle = TextStyle(
    color = Color.Black.copy(alpha = 0.6f),
    fontSize = TextUnit.Sp(18)
)

private val ValueStyle = TextStyle(
    color = Color.Black.copy(alpha = 0.9f),
    fontSize = TextUnit.Sp(32)
)

//private val ColumnChildModifier = Modifier.gravity(Alignment.CenterHorizontally).padding(8.dp)
