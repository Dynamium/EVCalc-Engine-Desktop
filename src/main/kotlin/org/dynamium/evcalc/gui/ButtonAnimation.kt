package org.dynamium.evcalc.gui

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.dp


val width = DpPropKey()
val corners = DpPropKey()
val textOpacity = FloatPropKey()

val transitionDefinition = transitionDefinition<ButtonState> {

    state(ButtonState.IDLE) {
        this[width] = 500.dp
        this[corners] = 6.dp
        this[textOpacity] = 1F
    }

    state(ButtonState.PRESSED) {
        this[width] = 60.dp
        this[corners] = 20.dp
        this[textOpacity] = 1F
    }

    transition(fromState = ButtonState.IDLE, toState = ButtonState.PRESSED) {
        width using tween(durationMillis = 500)

        corners using tween(durationMillis = 500, easing = FastOutLinearInEasing)

        textOpacity using keyframes {
            durationMillis = 500

            0F at 250
            1F at 500
        }
    }

    transition(fromState = ButtonState.PRESSED, toState = ButtonState.IDLE) {
        width using tween(durationMillis = 500)

        corners using tween(durationMillis = 500, easing = FastOutLinearInEasing)

        textOpacity using keyframes {
            durationMillis = 500

            0F at 250
            1F at 500
        }
    }
}