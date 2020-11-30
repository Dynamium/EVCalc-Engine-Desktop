package org.dynamium.evcalc.gui

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp


val width = DpPropKey()

val transitionDefinition = transitionDefinition<ButtonState> {

    state(ButtonState.IDLE) {
        this[width] = 500.dp
    }

    state(ButtonState.PRESSED) {
        this[width] = 60.dp
    }

    transition(fromState = ButtonState.IDLE, toState = ButtonState.PRESSED) {
        width using tween(durationMillis = 1500)
    }
}