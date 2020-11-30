@file:Suppress("FunctionName")

package org.dynamium.evcalc.gui

import androidx.compose.animation.transition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc
import java.lang.Integer.parseInt
import java.time.format.TextStyle

@Composable
fun MainScreen() {
    Box(
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

        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "EVCalc Engine"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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

                val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

                val toState = if (buttonState.value == ButtonState.IDLE) {
                    ButtonState.PRESSED
                } else {
                    ButtonState.IDLE
                }

                val state = transition(
                    definition = transitionDefinition,
                    initState = buttonState.value,
                    toState = toState // 2
                )

                Button(
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .size(state[width], 50.dp),
                    onClick = {
                        try {
                            textResult.value = EVCalc.calculateMileage(
                                DeviceModel.EUC_UNIVERSAL,
                                textFieldRiderWeight.value.text.toInt(),
                                textFieldBatteryCapacity.value.text.toInt(),
                                textFieldAirTemperature.value.text.toInt(),
                                textFieldBatteryCycles.value.text.toInt(),
                                textFieldSpeed.value.text.toInt(),
                                textFieldBatteryPercentage.value.text.toInt()
                            ).toString()
                            buttonState.value = if (buttonState.value == ButtonState.IDLE) {
                                ButtonState.PRESSED
                            } else {
                                ButtonState.IDLE
                            }
                        } catch (e: Throwable) {
                            textResult.value = "Что то пошло не так :("
                        }
                    },
                    enabled = true
                ) {
                    Text("Подсчитать!")
                }
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
                    Text(
                        style = (MaterialTheme.typography).h5,
                        text = textResult.value
                    )
                }
            }
        }
    }
}