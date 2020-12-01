@file:Suppress("FunctionName")

package org.dynamium.evcalc.desktop

import androidx.compose.animation.transition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.ExperimentalKeyInput
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.shortcuts
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.dynamium.evcalc.desktop.components.CalculateButton

@ExperimentalKeyInput
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        val textFieldRiderWeight = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryCapacity = remember { mutableStateOf(TextFieldValue()) }
        val textFieldAirTemperature = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryCycles = remember { mutableStateOf(TextFieldValue()) }
        val textFieldSpeed = remember { mutableStateOf(TextFieldValue()) }
        val textFieldBatteryPercentage = remember { mutableStateOf(TextFieldValue()) }

        val textResult = remember { mutableStateOf("Вы пока ничего не считали :D") }

        val textResultState = remember { mutableStateOf(ResultTextAnimationState.SHOWN) }

        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = {
                            bottomSheetState.hide()
                        }
                    ) {
                        Text("Готово")
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .height(50.dp),
                        bitmap = imageResource("EVCalc long logo.png")
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
                        OutlinedButton(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            onClick = {
                                bottomSheetState.show()
                            },
                            border = BorderStroke(2f.dp, Color(0x88B6B6B6))
                        ) {
                            Text("Дополнительные настройки")
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
    }
}