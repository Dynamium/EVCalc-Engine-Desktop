package org.dynamium.evcalc.desktop

import androidx.compose.desktop.Window
import androidx.compose.material.ExperimentalMaterialApi
import org.dynamium.evcalc.desktop.components.theme.EvcalcTheme
import org.dynamium.evcalc.engine.api.ExperimentalEvcalcApi

@ExperimentalEvcalcApi
@ExperimentalMaterialApi
fun main() {
    Window(title = "EVCalc") {
        EvcalcTheme {
            MainScreen()
        }
    }
}