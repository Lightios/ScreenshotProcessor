package pl.ppistudio.screenshotprocessor.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*

@Composable
fun ValidatedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    errorMessage: String?
) {
    Column {
        TextField(
            value = text,
            onValueChange = { newText ->
                onTextChange(newText)
            },
            label = { Text("Enter text") },
            isError = errorMessage != null
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = androidx.compose.ui.graphics.Color.Red
            )
        }
    }
}

fun validateText(text: String): String {
    return if (text.length < 5) {
        "Text must be at least 5 characters long"
    } else {
        ""
    }
}