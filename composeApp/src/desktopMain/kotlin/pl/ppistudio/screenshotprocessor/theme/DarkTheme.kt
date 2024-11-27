package pl.ppistudio.screenshotprocessor.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

@Composable
fun DarkTheme(content: @Composable () -> Unit) {
    val darkColors = darkColors(
        primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
        primaryVariant = androidx.compose.ui.graphics.Color(0xFF3700B3),
        secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6),
        background = androidx.compose.ui.graphics.Color(0xFF0000),
        surface = androidx.compose.ui.graphics.Color(0xFF121212),
        onPrimary = androidx.compose.ui.graphics.Color.Black,
        onSecondary = androidx.compose.ui.graphics.Color.Black,
        onBackground = androidx.compose.ui.graphics.Color.White,
        onSurface = androidx.compose.ui.graphics.Color.White
    )

    MaterialTheme(
        colors = darkColors,
        content = content
    )
}