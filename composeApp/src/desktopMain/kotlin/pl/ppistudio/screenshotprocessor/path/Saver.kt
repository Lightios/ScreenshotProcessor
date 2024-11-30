package pl.ppistudio.screenshotprocessor.path

import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun resizeBufferedImage(inputImage: BufferedImage, newWidth: Int, newHeight: Int): BufferedImage {
    val resizedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)
    val graphics: Graphics2D = resizedImage.createGraphics()
    graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
    graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    graphics.drawImage(inputImage, 0, 0, newWidth, newHeight, null)
    graphics.dispose()
    return resizedImage
}

fun saveImageToFile(image: BufferedImage, fileName: String): String? {
    val outputFile = File(fileName)

    if (outputFile.exists())
        return "File already exists"

    ImageIO.write(image, "jpg", outputFile)
    return null
}
