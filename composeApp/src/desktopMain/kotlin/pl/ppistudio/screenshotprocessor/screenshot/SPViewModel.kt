package pl.ppistudio.screenshotprocessor.screenshot

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import pl.ppistudio.screenshotprocessor.model.Ability
import pl.ppistudio.screenshotprocessor.model.agentToAbilities
import pl.ppistudio.screenshotprocessor.model.get
import java.awt.Graphics2D
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

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
}

fun resizeBufferedImage(inputImage: BufferedImage, newWidth: Int, newHeight: Int): BufferedImage {
    val resizedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)
    val graphics: Graphics2D = resizedImage.createGraphics()
    graphics.drawImage(inputImage, 0, 0, newWidth, newHeight, null)
    graphics.dispose()
    return resizedImage
}


fun saveImageToFile(image: BufferedImage, fileName: String) {
    val outputFile = File(fileName)
    ImageIO.write(image, "png", outputFile)
}
