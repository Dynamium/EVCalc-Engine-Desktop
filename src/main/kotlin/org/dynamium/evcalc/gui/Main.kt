package org.dynamium.evcalc.gui

import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme

fun main() = Window(title = "EVCalc") {
    MaterialTheme {
        MainScreen()
    }
}

