@file:Suppress("FunctionName")

package org.dynamium.evcalc.desktop

import androidx.compose.animation.transition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.maxLinesHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.dynamium.evcalc.desktop.components.CalculateButton
import org.dynamium.evcalc.desktop.components.CalculateButtonState
import org.dynamium.evcalc.desktop.components.calculateButtonTransitionDefinition
import org.dynamium.evcalc.engine.api.DeviceModel

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
        val textFieldBatteryCycles = remember { mutableStateOf(TextFieldValue("100")) }
        val textFieldSpeed = remember { mutableStateOf(TextFieldValue()) }
        val dropdownMenuDeviceModel = remember { mutableStateOf(DeviceModel.EUC_UNIVERSAL) }
        val dropdownMenuCalculationMode = remember { mutableStateOf(CalculationMode.MILEAGE) }
        val dropdownMenuDeviceModelReadable = remember { mutableStateOf("Не выбрано") }
        val dropdownMenuCalculationModeReadable = remember { mutableStateOf("Пробег") }
        val textFieldBatteryPercentage = remember { mutableStateOf(TextFieldValue("100")) }

        val textResult = remember { mutableStateOf("Вы пока ничего не считали :D") }

        val textResultState = remember { mutableStateOf(ResultTextAnimationState.SHOWN) }

        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

        val isDeviceModelDropdownExpanded = remember { mutableStateOf(false) }
        val isCalculationModeDropdownExpanded = remember { mutableStateOf(false) }

        val areDropdownsExpanded = arrayOf(isDeviceModelDropdownExpanded, isCalculationModeDropdownExpanded)

        val dropdownMenuReadables = arrayOf(dropdownMenuDeviceModelReadable, dropdownMenuCalculationModeReadable)

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 0.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = "Расширенные настройки",
                        style = MaterialTheme.typography.h5
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            val arraySize = areDropdownsExpanded.size - 1

                            val startingText = arrayOf("Девайс: ", "Считаем: ")

                            for (i in 0..arraySize) {
                                DropdownMenu(
                                    expanded = areDropdownsExpanded[i].value,
                                    onDismissRequest = { areDropdownsExpanded[i].value = false },
                                    toggle = @Composable {
                                        Button(
                                            onClick = {
                                                areDropdownsExpanded[i].value = true
                                            }
                                        ) {
                                            Text("${startingText[i]}${dropdownMenuReadables[i].value}")
                                            Icon(Icons.Default.ArrowDropDown)
                                        }
                                    },
                                    toggleModifier = Modifier
                                        .wrapContentSize(Alignment.TopStart)
                                ) {
                                    when (i) {
                                        0 -> {
                                            DropdownMenuItem(
                                                onClick = {
                                                    GlobalScope.launch {
                                                        delay(200L)
                                                        isDeviceModelDropdownExpanded.value = false
                                                        delay(200L)
                                                        dropdownMenuDeviceModel.value = DeviceModel.EUC_UNIVERSAL
                                                        dropdownMenuDeviceModelReadable.value = "Не выбрано"
                                                    }
                                                }
                                            ) {
                                                Text("Не выбрано")
                                            }
                                            Divider()
                                            DropdownMenuItem(
                                                onClick = {
                                                    GlobalScope.launch {
                                                        delay(200L)
                                                        isDeviceModelDropdownExpanded.value = false
                                                        delay(200L)
                                                        dropdownMenuDeviceModel.value = DeviceModel.EUC_UNIVERSAL
                                                        dropdownMenuDeviceModelReadable.value =
                                                            "Моноколесо (Универсально)"
                                                    }
                                                }
                                            ) {
                                                Text("Моноколесо (Универсально)")
                                            }
                                        }

                                        1 -> {
                                            DropdownMenuItem(
                                                onClick = {
                                                    GlobalScope.launch {
                                                        delay(200L)
                                                        isCalculationModeDropdownExpanded.value = false
                                                        delay(200L)
                                                        dropdownMenuCalculationMode.value = CalculationMode.MILEAGE
                                                        dropdownMenuCalculationModeReadable.value = "Пробег"
                                                    }
                                                }
                                            ) {
                                                Text("Пробег")
                                            }
                                            Divider()
                                            DropdownMenuItem(
                                                onClick = {
                                                    GlobalScope.launch {
                                                        delay(200L)
                                                        isCalculationModeDropdownExpanded.value = false
                                                        delay(200L)
                                                        dropdownMenuCalculationMode.value =
                                                            CalculationMode.TIRE_PRESSURE
                                                        dropdownMenuCalculationModeReadable.value =
                                                            "Давление в покрышке"
                                                    }
                                                }
                                            ) {
                                                Text("Давление в покрышке")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (dropdownMenuCalculationMode.value == CalculationMode.MILEAGE) {
                            Column(
                                modifier = Modifier,
                                horizontalAlignment = Alignment.CenterHorizontally
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
                                            value = textFieldBatteryCycles.value,
                                            onValueChange = { textFieldBatteryCycles.value = it },
                                            label = { Text(text = "Цыклы зарядки батареи") }
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                    ) {
                                        OutlinedTextField(
                                            value = textFieldBatteryPercentage.value,
                                            onValueChange = { textFieldBatteryPercentage.value = it },
                                            label = { Text(text = "Заряд батареи") }
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(
                                        top = if (dropdownMenuCalculationMode.value == CalculationMode.MILEAGE) {
                                            0.dp
                                        } else {
                                            16.dp
                                        }
                                    ),
                                onClick = {
                                    bottomSheetState.hide()
                                }
                            ) {
                                Text("Готово")
                            }
                        }
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
                            if (dropdownMenuCalculationMode.value == CalculationMode.MILEAGE) {

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

                            } else {

                                OutlinedTextField(
                                    value = textFieldRiderWeight.value,
                                    onValueChange = { textFieldRiderWeight.value = it },
                                    label = { Text(text = "Вес райдера") }
                                )


                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            if (dropdownMenuCalculationMode.value == CalculationMode.MILEAGE) {
                                OutlinedTextField(
                                    value = textFieldAirTemperature.value,
                                    onValueChange = { textFieldAirTemperature.value = it },
                                    label = { Text(text = "Температура воздуха") }
                                )
                                OutlinedTextField(
                                    value = textFieldSpeed.value,
                                    onValueChange = { textFieldSpeed.value = it },
                                    label = { Text(text = "Средняя скорость") }
                                )
                            } else {
                                val radioOptions = listOf(
                                    "Мягко, но можно повредить обод на кочке",
                                    "Средне, можно спрыгивать с высоких бордюров",
                                    "Твердо, для офроада"
                                )

                                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

                                radioOptions.forEach { text ->
                                    Row(
                                        Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .selectable(
                                                selected = (text == selectedOption),
                                                onClick = {
                                                    onOptionSelected(text)
                                                }
                                            )
                                            .padding(
                                                horizontal = 16.dp,
                                                vertical = 8.dp
                                            )
                                            .height(32.dp)
                                    ) {
                                        Column(
                                            Modifier
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            RadioButton(
                                                selected = (text == selectedOption),
                                                onClick = { onOptionSelected(text) }
                                            )
                                        }
                                        Column(
                                            Modifier
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.body1.merge(),
                                                modifier = Modifier.padding(start = 16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val buttonState = remember { mutableStateOf(CalculateButtonState.PRESSED) }

                        val toState = if (buttonState.value == CalculateButtonState.IDLE) {
                            CalculateButtonState.PRESSED
                        } else {
                            CalculateButtonState.IDLE
                        }

                        val state = transition(
                            definition = calculateButtonTransitionDefinition,
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
                            textFieldBatteryPercentage,
                            dropdownMenuCalculationMode,
                            dropdownMenuDeviceModel
                        )
                        OutlinedButton(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            onClick = {
                                bottomSheetState.show()
                            },
                            border = BorderStroke(2f.dp, Color(0x88B6B6B6))
                        ) {
                            Text("Расширенные настройки")
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