package pl.ppistudio.screenshotprocessor.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StringDropdownMenu(
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    onSelect: (String) -> Unit,
    onDismissRequest: () -> Unit,
    selectedItem: String?,
    items: List<String>,
    label: String
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange() },
//        { mapExpanded = !mapExpanded }
    ) {
        TextField(
            value = selectedItem ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth(0.2f)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismissRequest() }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(item)
                        onDismissRequest()
//                        mapExpanded = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}