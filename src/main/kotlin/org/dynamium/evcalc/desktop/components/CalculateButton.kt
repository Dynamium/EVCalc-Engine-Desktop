@file:Suppress("FunctionName", "unused")

package org.dynamium.evcalc.desktop.components

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc
import org.dynamium.evcalc.desktop.*

@Composable
fun CalculateButton(
    buttonState: MutableState<ButtonState>,
    textResultState: MutableState<ResultTextAnimationState>,
    state: TransitionState,
    textResult: MutableState<String>,
    riderWeight: MutableState<TextFieldValue>,
    batteryCapacity: MutableState<TextFieldValue>,
    airTemperature: MutableState<TextFieldValue>,
    batteryCycles: MutableState<TextFieldValue>,
    speed: MutableState<TextFieldValue>,
    batteryPercentage: MutableState<TextFieldValue>,
    deviceModel: MutableState<DeviceModel>
    ) {
    val buttonResult = remember { mutableStateOf("Подсчитать!") }

    Button(
        shape = RoundedCornerShape(state[corners]),
        modifier = Modifier
            .size(state[width], 50.dp),
        onClick = {
            try {
                val dummy = EVCalc.calculateMileage(
                    DeviceModel.EUC_UNIVERSAL,
                    riderWeight.value.text.toInt(),
                    batteryCapacity.value.text.toInt(),
                    airTemperature.value.text.toInt(),
                    batteryCycles.value.text.toInt(),
                    speed.value.text.toInt(),
                    batteryPercentage.value.text.toInt())

                drawCalculated(buttonResult, EVCalc.calculateMileage(
                    deviceModel.value,
                    riderWeight.value.text.toInt(),
                    batteryCapacity.value.text.toInt(),
                    airTemperature.value.text.toInt(),
                    batteryCycles.value.text.toInt(),
                    speed.value.text.toInt(),
                    batteryPercentage.value.text.toInt()
                ).toString())

                if (buttonState.value == ButtonState.IDLE) {
                    buttonState.value = ButtonState.PRESSED
                } else {
                    buttonState.value = ButtonState.IDLE
                    GlobalScope.launch {
                        delay(2000L)
                        buttonState.value = ButtonState.PRESSED
                        delay(250L)
                        buttonResult.value = "Подсчитать!"
                    }
                    GlobalScope.launch {
                        delay(2000L)
                        textResultState.value = ResultTextAnimationState.HIDDEN
                        delay(250L)
                        textResult.value = "Последнее подсчитанное: $dummy"
                        textResultState.value = ResultTextAnimationState.SHOWN
                    }
                }
            } catch (e: Throwable) {
                GlobalScope.launch {
                    textResultState.value = ResultTextAnimationState.HIDDEN
                    delay(250L)
                    textResult.value = "Что то пошло не так :("
                    textResultState.value = ResultTextAnimationState.SHOWN
                }
            }
        },
        enabled = true
    ) {
        Text(
            modifier = Modifier
                .alpha(state[textOpacity]),
            color = Color.White,
            text = buttonResult.value
        )
    }
}

fun drawCalculated(textResult: MutableState<String>, result: String) {
    GlobalScope.launch {
        delay(250L)
        textResult.value = result
    }
}