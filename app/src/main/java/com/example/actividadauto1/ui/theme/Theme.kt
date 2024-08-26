package com.example.actividadauto1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LightColorScheme = lightColorScheme(
    primary = Blue500,
    secondary = Teal200,
    tertiary = Blue200
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue200,
    secondary = Teal200,
    tertiary = Blue700
)

@Composable
fun ActividadAuto1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        val currentActivity = view.context as? Activity
        currentActivity?.window?.statusBarColor = colorScheme.primary.toArgb()
        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}