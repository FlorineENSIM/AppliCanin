package com.example.applicationcanin.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = HeaderColor,
    onPrimary = ColorText,

    background = fondApp,
    onBackground = ColorText,

    surface = interieurBloc,
    onSurface = ColorText,

    outline = fondBloc,

    tertiary = fondStory,
    onTertiary = ColorText,

    error = Color(0xFFE53935), // Tu peux aussi créer une val `ErrorRed`
    onError = Color.White
)

@Composable
fun ApplicationCaninTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}