package pl.ppistudio.screenshotprocessor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pl.ppistudio.screenshotprocessor.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        App()
    }
}