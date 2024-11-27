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
import pl.ppistudio.screenshotprocessor.model.Ability

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AbilityDropdownMenu(
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    onSelect: (Ability) -> Unit,
    onDismissRequest: () -> Unit,
    selectedItem: Ability?,
    items: List<Ability>,
    label: String
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange() },
    ) {
        TextField(
            value = selectedItem?.name ?: "",
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
                    }
                ) {
                    Text(text = item.name)
                }
            }
        }
    }
}