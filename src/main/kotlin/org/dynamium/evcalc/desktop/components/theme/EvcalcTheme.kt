@file:Suppress("FunctionName", "LocalVariableName")

package org.dynamium.evcalc.desktop.components.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.dynamium.evcalc.desktop.components.theme.Font.EvcalcTypography

@Composable
fun EvcalcTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        typography = EvcalcTypography,
        content = content
    )
}
