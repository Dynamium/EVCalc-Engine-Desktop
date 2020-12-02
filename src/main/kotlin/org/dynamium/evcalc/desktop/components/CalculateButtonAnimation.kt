package org.dynamium.evcalc.desktop.components

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.dp


val calculateButtonWidth = DpPropKey()
val calculateButtonCorners = DpPropKey()
val calculateButtonTextOpacity = FloatPropKey()

val calculateButtonTransitionDefinition = transitionDefinition<CalculateButtonState> {

    state(CalculateButtonState.IDLE) {
        this[calculateButtonWidth] = 500.dp
        this[calculateButtonCorners] = 6.dp
        this[calculateButtonTextOpacity] = 1F
    }

    state(CalculateButtonState.PRESSED) {
        this[calculateButtonWidth] = 60.dp
        this[calculateButtonCorners] = 20.dp
        this[calculateButtonTextOpacity] = 1F
    }

    transition(fromState = CalculateButtonState.IDLE, toState = CalculateButtonState.PRESSED) {
        calculateButtonWidth using tween(durationMillis = 500)

        calculateButtonCorners using tween(durationMillis = 500, easing = FastOutLinearInEasing)

        calculateButtonTextOpacity using keyframes {
            durationMillis = 500

            0F at 250
            1F at 500
        }
    }

    transition(fromState = CalculateButtonState.PRESSED, toState = CalculateButtonState.IDLE) {
        calculateButtonWidth using tween(durationMillis = 500)

        calculateButtonCorners using tween(durationMillis = 500, easing = FastOutLinearInEasing)

        calculateButtonTextOpacity using keyframes {
            durationMillis = 500

            0F at 250
            1F at 500
        }
    }
}