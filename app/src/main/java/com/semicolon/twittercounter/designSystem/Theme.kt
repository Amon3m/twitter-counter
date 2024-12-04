package com.semicolon.A.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.semicolon.twittercounter.designSystem.TwitterColor
import com.semicolon.twittercounter.designSystem.LightColors
import com.semicolon.twittercounter.designSystem.Radius
import com.semicolon.twittercounter.designSystem.Typography
import com.semicolon.twittercounter.designSystem.body
import com.semicolon.twittercounter.designSystem.buttonTextLarge
import com.semicolon.twittercounter.designSystem.buttonTextRegular
import com.semicolon.twittercounter.designSystem.header
import com.semicolon.twittercounter.designSystem.title
import com.semicolon.twittercounter.designSystem.titleLarge

private val localColorScheme = staticCompositionLocalOf { LightColors }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localTypography = staticCompositionLocalOf { Typography() }

@Composable
fun TwitterCounterTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColors
    val typography = Typography(
        titleLarge = titleLarge(),
        title = title(),
        body = body(),
        header = header(),
        buttonTextLarge = buttonTextLarge(),
        buttonTextRegular = buttonTextRegular()
    )

    CompositionLocalProvider(
        localColorScheme provides colorScheme,
        localTypography provides typography,
        localRadius provides Radius(),
    ) {
        content()
    }
}

object Theme {
    val colors: TwitterColor
        @Composable
        @ReadOnlyComposable
        get() = localColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = localRadius.current

}