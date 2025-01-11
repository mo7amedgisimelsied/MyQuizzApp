package com.example.myquizzapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = buttons_color,
    secondary = correct_answer,
    tertiary = wrong_answer,
    background = background_color,
)

@Composable
fun MyQuizzAppTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}