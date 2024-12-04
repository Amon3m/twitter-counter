package com.semicolon.twittercounter.designSystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class Typography(
    val header: TextStyle = TextStyle(),
    val titleLarge: TextStyle = TextStyle(),
    val title: TextStyle = TextStyle(),
    val body: TextStyle = TextStyle(),
    val buttonTextLarge: TextStyle = TextStyle(),
    val buttonTextRegular: TextStyle = TextStyle()
)


@Composable
fun header(): TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        fontFamily = din,
        lineHeight = 25.sp,
        fontWeight = FontWeight.W500,
    )
}

@Composable
fun titleLarge(): TextStyle {
    return TextStyle(
        fontSize = 26.sp,
        lineHeight = 36.sp,
        fontFamily = din,
        fontWeight = FontWeight.W500,
    )
}


@Composable
fun title(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        fontFamily = din,
        fontWeight = FontWeight.W500,
    )
}

@Composable
fun body(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = din,
        lineHeight = 22.sp,
        fontWeight = FontWeight.W400,
    )
}

@Composable
fun buttonTextLarge(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontFamily = din,
        fontWeight = FontWeight.W500,
    )
}

@Composable
fun buttonTextRegular(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = din,
        fontWeight = FontWeight.W400,
    )
}