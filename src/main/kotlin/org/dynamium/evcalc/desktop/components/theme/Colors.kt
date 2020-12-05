package org.dynamium.evcalc.desktop.components.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Blue200 = Color(0xff14548F)
val Red300 = Color(0xff165D9F)

val Red700 = Color(0xff1C79CF)
val Red800 = Color(0xff1F83E1)
val Red900 = Color(0xff2395FF)

val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.White,
    error = Blue200
)