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
    riderWeight: MutableState<TextFieldValue>,
    batteryCapacity: MutableState<TextFieldValue>,
    airTemperature: MutableState<TextFieldValue>,
    batteryCycles: MutableState<TextFieldValue>,
    speed: MutableState<TextFieldValue>,
    batteryPercentage: MutableState<TextFieldValue>
    ) {
    Button(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .size(state[width], 50.dp),
        onClick = {
            try {
                textResult.value = EVCalc.calculateMileage(
                    DeviceModel.EUC_UNIVERSAL,
                    riderWeight.value.text.toInt(),
                    batteryCapacity.value.text.toInt(),
                    airTemperature.value.text.toInt(),
                    batteryCycles.value.text.toInt(),
                    speed.value.text.toInt(),
                    batteryPercentage.value.text.toInt()
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