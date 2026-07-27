package com.example.matchaai.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MatchaDarkColorScheme = darkColorScheme(
    primary          = MatchaGreen,
    onPrimary        = TextOnGreen,
    primaryContainer = BackgroundElevated,
    secondary        = MatchaGold,
    onSecondary      = BackgroundDeep,
    background       = BackgroundDeep,
    surface          = BackgroundCard,
    onSurface        = TextPrimary,
    onBackground     = TextPrimary,
    outline          = BackgroundBorder,
    surfaceVariant   = BackgroundElevated,
    error            = SignalRed,
)

@Composable
fun MatchaAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MatchaDarkColorScheme,
        typography  = MatchaTypography,
        content     = content
    )
}
