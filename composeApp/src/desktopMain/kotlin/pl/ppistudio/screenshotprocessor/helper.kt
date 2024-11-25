package pl.ppistudio.screenshotprocessor

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.image.BufferedImage

fun getImageFromClipboard(): BufferedImage? {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val data = clipboard.getContents(null)

    if (data != null && data.isDataFlavorSupported(DataFlavor.imageFlavor)) {
        return data.getTransferData(DataFlavor.imageFlavor) as BufferedImage
    }

    return null
}
