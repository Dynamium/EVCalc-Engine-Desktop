package org.dynamium.evcalc.desktop

import androidx.compose.desktop.Window
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.input.key.ExperimentalKeyInput

@ExperimentalMaterialApi
@ExperimentalKeyInput
fun main() {
    Window(title = "EVCalc") {
        MaterialTheme {
            MainScreen()
        }
    }
}