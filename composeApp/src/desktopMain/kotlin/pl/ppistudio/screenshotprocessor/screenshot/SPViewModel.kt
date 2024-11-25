package pl.ppistudio.screenshotprocessor.screenshot

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.awt.Graphics2D
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class SPViewModel: ViewModel() {
    private val _image = MutableStateFlow<BufferedImage?>(null)
    val image: StateFlow<BufferedImage?> = _image.asStateFlow()

    private val _selectedItem = MutableStateFlow<String?>(null)
    val selectedItem: StateFlow<String?> = _selectedItem.asStateFlow()

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

    fun selectItem(item: String) {
        _selectedItem.update { item }
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
