package com.bintangg.finalproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    primary = AccentPurple,
    onPrimary = Color.White,
    secondary = AccentBlue,
    onSecondary = Color.White,
    tertiary = AccentGreen,
    onTertiary = Color.White,
    background = DarkAppBg,
    onBackground = DarkMainText,
    surface = DarkPanel,
    onSurface = DarkMainText,
    surfaceVariant = DarkPanel2,
    onSurfaceVariant = DarkMuted,
    surfaceTint = Color.Transparent,
    surfaceContainer = DarkPanel,
    surfaceContainerLow = DarkAppBg,
    surfaceContainerLowest = DarkAppBg,
    surfaceContainerHigh = DarkPanel2,
    surfaceContainerHighest = DarkPanel2,
    outline = DarkBorder,
    outlineVariant = DarkBorder
)

@Composable
fun FinalProjectTheme(
    darkTheme: Boolean = true,
    // Always prioritize the custom dark dashboard theme requested by user
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
