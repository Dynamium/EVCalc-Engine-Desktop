package org.dynamium.evcalc.desktop

import androidx.compose.desktop.Window
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme

@ExperimentalMaterialApi
fun main() {
    Window(title = "EVCalc") {
        MaterialTheme {
            MainScreen()
        }
    }
}