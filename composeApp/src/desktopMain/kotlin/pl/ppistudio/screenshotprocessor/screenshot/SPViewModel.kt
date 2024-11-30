package pl.ppistudio.screenshotprocessor.screenshot

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.ppistudio.screenshotprocessor.composables.validateText
import pl.ppistudio.screenshotprocessor.model.Ability
import pl.ppistudio.screenshotprocessor.model.agentToAbilities
import pl.ppistudio.screenshotprocessor.model.get
import pl.ppistudio.screenshotprocessor.path.createFoldersIfNotExist
import pl.ppistudio.screenshotprocessor.path.createPath
import pl.ppistudio.screenshotprocessor.path.openExplorerAtPath
import pl.ppistudio.screenshotprocessor.path.resizeBufferedImage
import pl.ppistudio.screenshotprocessor.path.saveImageToFile
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.image.BufferedImage

class SPViewModel: ViewModel() {
    private val _image = MutableStateFlow<BufferedImage?>(null)
    val image = _image.asStateFlow()

    private val _selectedMap = MutableStateFlow<String?>(null)
    val selectedMap = _selectedMap.asStateFlow()

    private val _selectedAgent = MutableStateFlow<String?>(null)
    val selectedAgent = _selectedAgent.asStateFlow()

    private val _selectedAbility = MutableStateFlow<Ability?>(null)
    val selectedAbility = _selectedAbility.asStateFlow()

    private val _abilities = MutableStateFlow<List<Ability>>(emptyList())
    val abilities = _abilities.asStateFlow()

    private val _additionalOptions = MutableStateFlow<List<String>>(emptyList())
    val additionalOptions = _additionalOptions.asStateFlow()

    private val _selectedAdditionalOption = MutableStateFlow<String?>(null)
    val selectedAdditionalOption = _selectedAdditionalOption.asStateFlow()

    private val _enteredText = MutableStateFlow<String>("")
    val enteredText = _enteredText.asStateFlow()

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _imageMessage = MutableStateFlow<String>("")
    val imageMessage = _imageMessage.asStateFlow()

    fun enterText(text: String) {
        _enteredText.update { text }

        _errorMessage.update { validateText(text) }
    }

    fun getImageFromClipboard() {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val data = clipboard.getContents(null)

        if (data != null && data.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            val clipboardImage = data.getTransferData(DataFlavor.imageFlavor) as? BufferedImage
            if (clipboardImage != null) {
                _image.update { clipboardImage }
            }
        }
    }

    fun selectMap(map: String) {
        _selectedMap.update { map }
    }

    fun selectAgent(agent: String) {
        _selectedAgent.update { agent }

        _abilities.update { agentToAbilities(agent) }
        selectAbility(abilities.value.first())
    }

    fun selectAbility(ability: Ability) {
        _selectedAbility.update { ability }

        _additionalOptions.update { get(ability) }
        selectAdditionalOption(additionalOptions.value.first())
    }

    fun selectAdditionalOption(option: String) {
        _selectedAdditionalOption.update { option }
    }

    fun saveImage() {
        validateText(enteredText.value)
        _imageMessage.update { "" }

        if (selectedAbility.value == null || selectedMap.value == null || selectedAgent.value == null || enteredText.value == "") {
            return
        }

        image.value?.let {
            _errorMessage.update { "" }
            val image = resizeBufferedImage(it, 1080, 608)
            val path = createPath(
                ability = selectedAbility.value!!,
                additionalOption = selectedAdditionalOption.value!!,
                map = selectedMap.value!!,
                fileName = enteredText.value
            )

            createFoldersIfNotExist(path)
            val error = saveImageToFile(image, path)

            if (error == null) {
                _imageMessage.update { "${_enteredText.value} saved " }
            } else {
                _errorMessage.update { error }
            }
        } ?: run {
            _errorMessage.update { "No image to save" }
        }

    }

    fun openFolder() {
        val path = createPath(
            ability = selectedAbility.value!!,
            additionalOption = selectedAdditionalOption.value!!,
            map = selectedMap.value!!,

        )
        openExplorerAtPath(path)
    }
}


