package org.dynamium.evcalc.desktop.components.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.platform.font
import androidx.compose.ui.unit.sp

object Font {
    private val Montserrat = fontFamily(
        font(
            "Montserrat SemiBold",
            "font/montserrat/semibold.ttf",
            FontWeight.SemiBold,
            FontStyle.Normal
        ),
        font(
            "Montserrat Black",
            "font/montserrat/black.ttf",
            FontWeight.Black,
            FontStyle.Normal
        ),
        font(
            "Montserrat Black Italic",
            "font/montserrat/blackitalic.ttf",
            FontWeight.Black,
            FontStyle.Italic
        ),
        font(
            "Montserrat Bold",
            "font/montserrat/bold.ttf",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "Montserrat Bold Italic",
            "font/montserrat/bolditalic.ttf",
            FontWeight.Bold,
            FontStyle.Italic
        ),
        font(
            "Montserrat Extra Bold",
            "font/montserrat/extrabold.ttf",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        font(
            "Montserrat Extra Bold Italic",
            "font/montserrat/extrabolditalic.ttf",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),
        font(
            "Montserrat Extra Light",
            "font/montserrat/extralight.ttf",
            FontWeight.ExtraLight,
            FontStyle.Normal
        ),
        font(
            "Montserrat Extra Light Italic",
            "font/montserrat/extralightitalic.ttf",
            FontWeight.ExtraLight,
            FontStyle.Italic
        ),
        font(
            "Montserrat Italic",
            "font/montserrat/italic.ttf",
            FontWeight.Normal,
            FontStyle.Italic
        ),
        font(
            "Montserrat Regular",
            "font/montserrat/regular.ttf",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "Montserrat Light",
            "font/montserrat/light.ttf",
            FontWeight.Light,
            FontStyle.Normal
        ),
        font(
            "Montserrat Light Italic",
            "font/montserrat/lightitalic.ttf",
            FontWeight.Light,
            FontStyle.Italic
        ),
        font(
            "Montserrat Medium",
            "font/montserrat/medium.ttf",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        font(
            "Montserrat Medium Italic",
            "font/montserrat/mediumitalic.ttf",
            FontWeight.Medium,
            FontStyle.Italic
        ),
        font(
            "Montserrat SemiBold Italic",
            "font/montserrat/semibolditalic.ttf",
            FontWeight.SemiBold,
            FontStyle.Italic
        ),
        font(
            "Montserrat Thin",
            "font/montserrat/thin.ttf",
            FontWeight.Thin,
            FontStyle.Normal
        ),
        font(
            "Montserrat Thin Italic",
            "font/montserrat/thinitalic.ttf",
            FontWeight.Thin,
            FontStyle.Italic
        )
    )

    val EvcalcTypography = Typography(
        h4 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp
        ),
        h5 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp
        ),
        h6 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        body1 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    )
}