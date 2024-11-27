package pl.ppistudio.screenshotprocessor

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import pl.ppistudio.screenshotprocessor.screenshot.MainScreen
import pl.ppistudio.screenshotprocessor.screenshot.SPViewModel
import pl.ppistudio.screenshotprocessor.theme.DarkTheme

@Composable
@Preview
fun App() {
    MaterialTheme{
        val viewModel = SPViewModel()

        MainScreen(viewModel)
    }
}