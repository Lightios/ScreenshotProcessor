package pl.ppistudio.screenshotprocessor.path

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun resizeBufferedImage(inputImage: BufferedImage, newWidth: Int, newHeight: Int): BufferedImage {
    val resizedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)
    val graphics: Graphics2D = resizedImage.createGraphics()
    graphics.drawImage(inputImage, 0, 0, newWidth, newHeight, null)
    graphics.dispose()
    return resizedImage
}


fun saveImageToFile(image: BufferedImage, fileName: String): String? {
    val outputFile = File(fileName)

    if (outputFile.exists())
        return "File already exists"

    ImageIO.write(image, "png", outputFile)
    return null
}
