package pl.ppistudio.screenshotprocessor.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.ppistudio.screenshotprocessor.composables.AbilityDropdownMenu
import pl.ppistudio.screenshotprocessor.composables.StringDropdownMenu
import pl.ppistudio.screenshotprocessor.composables.ValidatedTextField
import pl.ppistudio.screenshotprocessor.composables.validateText
import pl.ppistudio.screenshotprocessor.model.AGENTS
import pl.ppistudio.screenshotprocessor.model.AbilityType
import pl.ppistudio.screenshotprocessor.model.MAPS


@Composable
fun MainScreen(
    viewModel: SPViewModel,
) {
    val image by viewModel.image.collectAsStateWithLifecycle()

    var mapExpanded by remember { mutableStateOf(false) }
    var agentExpanded by remember { mutableStateOf(false) }
    var abilityExpanded by remember { mutableStateOf(false) }
    var additionalOptionExpanded by remember { mutableStateOf(false) }

    val abilities by viewModel.abilities.collectAsStateWithLifecycle()
    val additionalOptions by viewModel.additionalOptions.collectAsStateWithLifecycle()

    val selectedMap by viewModel.selectedMap.collectAsStateWithLifecycle()
    val selectedAgent by viewModel.selectedAgent.collectAsStateWithLifecycle()
    val selectedAbility by viewModel.selectedAbility.collectAsStateWithLifecycle()
    val selectedAdditionalOption by viewModel.selectedAdditionalOption.collectAsStateWithLifecycle()

    var text by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Row(
    ){
        Column(
            modifier = Modifier.fillMaxHeight().weight(1f).padding(10.dp),
        ) {
            Button(
                onClick = viewModel::getImageFromClipboard
            ) {
                Text("Paste image from clipboard")
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                if (image != null) {
                    Image(
                        bitmap = image!!.toComposeImageBitmap(),
                        contentDescription = "Image from clipboard"
                    )
                } else {
                    Text("No image")
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            StringDropdownMenu(
                expanded = mapExpanded,
                onExpandedChange = { mapExpanded = !mapExpanded },
                onSelect = viewModel::selectMap,
                onDismissRequest = { mapExpanded = false },
                selectedItem = selectedMap,
                items = MAPS,
                label = "Select Map"
            )

            StringDropdownMenu(
                expanded = agentExpanded,
                onExpandedChange = { agentExpanded = !agentExpanded },
                onSelect = viewModel::selectAgent,
                onDismissRequest = { agentExpanded = false },
                selectedItem = selectedAgent,
                items = AGENTS,
                label = "Select Agent"
            )

            AbilityDropdownMenu(
                expanded = abilityExpanded,
                onExpandedChange = { abilityExpanded = !abilityExpanded },
                onSelect = viewModel::selectAbility,
                onDismissRequest = { abilityExpanded = false },
                selectedItem = selectedAbility,
                items = abilities,
                label = "Select Ability"
            )

            if (selectedAbility?.type in listOf(AbilityType.DEFAULT, AbilityType.POSTPLANT)) {
                StringDropdownMenu(
                    expanded = additionalOptionExpanded,
                    onExpandedChange = { additionalOptionExpanded = !additionalOptionExpanded },
                    onSelect = viewModel::selectAdditionalOption,
                    onDismissRequest = { additionalOptionExpanded = false },
                    selectedItem = selectedAdditionalOption,
                    items = additionalOptions,
                    label = "Select"
                )
            }

            ValidatedTextField(
                text = text,
                onTextChange = { newText ->
                    text = newText
                    errorMessage = validateText(newText)
                },
                errorMessage = errorMessage
            )


            Button(
                onClick = {}
            ) {
                Text("Save")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val viewModel = SPViewModel()
    MainScreen(viewModel = viewModel)
}