@file:Suppress("FunctionName")

package org.dynamium.evcalc.gui

import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.dynamium.evcalc.gui.components.CalculateButton

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val textFieldRiderWeight = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryCapacity = remember { mutableStateOf(TextFieldValue()) }
        val textFieldAirTemperature = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryCycles = remember { mutableStateOf(TextFieldValue()) }
        val textFieldSpeed = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryPercentage = remember { mutableStateOf(TextFieldValue()) }

        val textResult = remember { mutableStateOf("Вы пока ничего не считали :D") }

        val textResultState = remember { mutableStateOf(ResultTextAnimationState.SHOWN) }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(50.dp),
                bitmap = imageResource("EVCalc Long logo.png")
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = textFieldRiderWeight.value,
                        onValueChange = { textFieldRiderWeight.value = it },
                        label = { Text(text = "Вес райдера") }
                    )
                    OutlinedTextField(
                        value = textFieldBatteryCapacity.value,
                        onValueChange = { textFieldBatteryCapacity.value = it },
                        label = { Text(text = "Емкость батареи") }
                    )
                    OutlinedTextField(
                        value = textFieldAirTemperature.value,
                        onValueChange = { textFieldAirTemperature.value = it },
                        label = { Text(text = "Температура воздуха") }
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = textFieldBatteryCycles.value,
                        onValueChange = { textFieldBatteryCycles.value = it },
                        label = { Text(text = "Цыклы зарядки батареи") }
                    )
                    OutlinedTextField(
                        value = textFieldSpeed.value,
                        onValueChange = { textFieldSpeed.value = it },
                        label = { Text(text = "Средняя скорость") }
                    )
                    OutlinedTextField(
                        value = textFieldBatteryPercentage.value,
                        onValueChange = { textFieldBatteryPercentage.value = it },
                        label = { Text(text = "Заряд батареи") }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val buttonState = remember { mutableStateOf(ButtonState.PRESSED) }

                val toState = if (buttonState.value == ButtonState.IDLE) {
                    ButtonState.PRESSED
                } else {
                    ButtonState.IDLE
                }

                val state = transition(
                    definition = transitionDefinition,
                    initState = buttonState.value,
                    toState = toState
                )

                CalculateButton(
                    buttonState,
                    textResultState,
                    state,
                    textResult,
                    textFieldRiderWeight,
                    textFieldBatteryCapacity,
                    textFieldAirTemperature,
                    textFieldBatteryCycles,
                    textFieldSpeed,
                    textFieldBatteryPercentage
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val toState = if (textResultState.value == ResultTextAnimationState.SHOWN) {
                        ResultTextAnimationState.HIDDEN
                    } else {
                        ResultTextAnimationState.SHOWN
                    }

                    val state = transition(
                        definition = resultTextTransitionDefinition,
                        initState = textResultState.value,
                        toState = toState
                    )
                    Text(
                        modifier = Modifier
                            .alpha(state[resultTextOpacity]),
                        style = (MaterialTheme.typography).h5,
                        text = textResult.value
                    )
                }
            }
        }
    }
}