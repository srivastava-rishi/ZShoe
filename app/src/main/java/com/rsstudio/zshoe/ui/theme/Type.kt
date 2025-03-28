package com.rsstudio.zshoe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.rsstudio.zshoe.alias.AppFont

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object Fonts {

    val avenirRegular = FontFamily(
        Font(AppFont.avenir_regular, FontWeight.Normal)
    )

    val avenirBlack = FontFamily(
        Font(AppFont.avenir_black, FontWeight.Black)
    )

    val avenirBold = FontFamily(
        Font(AppFont.avenir_extra_bold, FontWeight.Bold)
    )
    val avenirThin = FontFamily(
        Font(AppFont.avenir_thin, FontWeight.ExtraLight)
    )
}

val Typography.label: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.avenirRegular,
        fontSize = 14.sp,
        color = lightBlack
    )

val Typography.ht1: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.avenirBlack,
        fontSize = 14.sp,
        color = white
    )

val Typography.subTitle: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.avenirBold,
        fontSize = 18.sp,
        color = white
    )
val Typography.labelSmall2: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.avenirThin,
        fontSize = 14.sp,
        color = white
    )
