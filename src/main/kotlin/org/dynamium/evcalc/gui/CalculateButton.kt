@file:Suppress("FunctionName", "unused")

package org.dynamium.evcalc.gui

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc

@Composable
fun CalculateButton(
    buttonState: MutableState<ButtonState>,
    state: TransitionState,
    textResult: MutableState<String>,
    textFieldRiderWeight: MutableState<TextFieldValue>,
    textFieldBatteryCapacity: MutableState<TextFieldValue>,
    textFieldAirTemperature: MutableState<TextFieldValue>,
    textFieldBatteryCycles: MutableState<TextFieldValue>,
    textFieldSpeed: MutableState<TextFieldValue>,
    textFieldBatteryPercentage: MutableState<TextFieldValue>
    ) {
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