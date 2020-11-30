package org.dynamium.evcalc.gui

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween

val resultTextOpacity = FloatPropKey()

val resultTextTransitionDefinition = transitionDefinition<ResultTextAnimationState> {
    state(ResultTextAnimationState.SHOWN) {
        this[resultTextOpacity] = 0F
    }

    state(ResultTextAnimationState.HIDDEN) {
        this[resultTextOpacity] = 1F
    }

    transition(fromState = ResultTextAnimationState.SHOWN, toState = ResultTextAnimationState.HIDDEN) {
        resultTextOpacity using tween(durationMillis = 250)
    }

    transition(fromState = ResultTextAnimationState.HIDDEN, toState = ResultTextAnimationState.SHOWN) {
        resultTextOpacity using tween(durationMillis = 250)
    }
}

enum class ResultTextAnimationState {
    SHOWN,
    HIDDEN
}