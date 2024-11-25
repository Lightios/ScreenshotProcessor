package pl.ppistudio.screenshotprocessor.screenshot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.ppistudio.screenshotprocessor.model.LINEUP_TYPES


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: SPViewModel
) {
    val image by viewModel.image.collectAsStateWithLifecycle()
    val selectedItem by viewModel.selectedItem.collectAsStateWithLifecycle()
    val items = LINEUP_TYPES
    var expanded by remember { mutableStateOf(false) }

    Row(){
        Column(
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            Button(
                onClick = viewModel::getImageFromClipboard
            ) {
                Text("Wklej obraz ze schowka")
            }

            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                if (image != null) {
                    Image(image!!.toComposeImageBitmap(), contentDescription = "Obraz ze schowka")

                } else {
                    Text("Brak obrazu w schowku")
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedItem ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Item") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.fillMaxWidth(0.2f)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.selectItem(item)
                                expanded = false
                            }
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }

            Button(
                onClick = {}
            ) {
                Text("Save")
            }

        }
    }

}



//Button(onClick = {
//    // Zmiana rozmiaru i zapis
////                val resizedImage = resizeBufferedImage(image!!, 1080, 608)
////                saveImageToFile(resizedImage, "resized_image.png")
//}) {
//    Text("Zapisz obrazek 1080x608")
//}