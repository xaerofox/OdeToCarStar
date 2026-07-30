package com.jtorr.odetocarstar.presentation.util.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// Font families with system fallbacks
// Anton: Anton (sans-serif fallback)
val FontAnton = FontFamily.SansSerif
// Geist: Inter (sans-serif fallback)
val FontGeist = FontFamily.SansSerif
// JetBrains Mono: Monospace fallback
val FontJetBrainsMono = FontFamily.Monospace

val Typography = Typography(
    // Display LG
    displayLarge = TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 72.sp,
        lineHeight = 72.sp,
        letterSpacing = 0.02.em
    ),
    // Display MD
    displayMedium = TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 53.sp,
        letterSpacing = 0.04.em
    ),
    // Display SM
    displaySmall = TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 35.sp
    ),
    // Headline MD
    headlineMedium = TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.04.em
    ),
    // Body LG
    bodyLarge = TextStyle(
        fontFamily = FontGeist,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 29.sp
    ),
    // Body MD
    bodyMedium = TextStyle(
        fontFamily = FontGeist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // Label Caps (uppercase monospaced)
    labelLarge = TextStyle(
        fontFamily = FontJetBrainsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.1.em
    ),
    // Data Numeric (monospaced for telemetry)
    bodySmall = TextStyle(
        fontFamily = FontJetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 20.sp
    )
)
