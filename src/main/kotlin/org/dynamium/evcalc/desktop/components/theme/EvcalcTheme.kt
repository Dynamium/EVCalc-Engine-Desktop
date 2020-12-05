@file:Suppress("FunctionName", "LocalVariableName")

package org.dynamium.evcalc.desktop.components.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.dynamium.evcalc.desktop.components.theme.Font.EvcalcTypography

@Composable
fun EvcalcTheme(content: @Composable () -> Unit) {
    val LightColors = lightColors(
        primary = Red700,
        primaryVariant = Red900,
        onPrimary = Color.White,
        secondary = Red700,
        secondaryVariant = Red900,
        onSecondary = Color.White,
        error = Red800
    )
    MaterialTheme(
        colors = LightColors,
        typography = EvcalcTypography,
        content = content
    )
}
