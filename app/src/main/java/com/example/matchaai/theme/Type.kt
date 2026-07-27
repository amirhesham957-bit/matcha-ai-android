package com.example.matchaai.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val MatchaTypography = Typography(
    displayLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Black,
        fontSize    = 57.sp,
        letterSpacing = (-0.25).sp,
        color       = TextPrimary
    ),
    displayMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Bold,
        fontSize    = 45.sp,
        letterSpacing = 0.sp,
        color       = TextPrimary
    ),
    headlineLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.ExtraBold,
        fontSize    = 32.sp,
        letterSpacing = (-0.5).sp,
        color       = TextPrimary
    ),
    headlineMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Bold,
        fontSize    = 24.sp,
        letterSpacing = (-0.25).sp,
        color       = TextPrimary
    ),
    headlineSmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.SemiBold,
        fontSize    = 20.sp,
        letterSpacing = 0.sp,
        color       = TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.SemiBold,
        fontSize    = 18.sp,
        letterSpacing = 0.sp,
        color       = TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Medium,
        fontSize    = 14.sp,
        letterSpacing = 0.1.sp,
        color       = TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 16.sp,
        letterSpacing = 0.5.sp,
        color       = TextSecondary
    ),
    bodyMedium = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Normal,
        fontSize    = 14.sp,
        letterSpacing = 0.25.sp,
        color       = TextSecondary
    ),
    labelLarge = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.SemiBold,
        fontSize    = 12.sp,
        letterSpacing = 1.sp,
        color       = TextSecondary
    ),
    labelSmall = TextStyle(
        fontFamily  = FontFamily.Default,
        fontWeight  = FontWeight.Medium,
        fontSize    = 10.sp,
        letterSpacing = 1.5.sp,
        color       = TextMuted
    ),
)
