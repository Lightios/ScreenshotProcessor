//package pl.ppistudio.screenshotprocessor
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.graphics.toComposeImageBitmap
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import java.awt.Graphics2D
//import java.awt.image.BufferedImage
//import java.io.File
//import javax.imageio.ImageIO
//
//fun resizeBufferedImage(inputImage: BufferedImage, newWidth: Int, newHeight: Int): BufferedImage {
//    val resizedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)
//    val graphics: Graphics2D = resizedImage.createGraphics()
//    graphics.drawImage(inputImage, 0, 0, newWidth, newHeight, null)
//    graphics.dispose()
//    return resizedImage
//}
//
//@Composable
//fun MainScreen(
//    viewModel: SPViewModel
//) {
////    var image by remember { mutableStateOf<BufferedImage?>(null) }
////    val image by viewModel.image.collectAsStateWithLifecycle()
//    val image by viewModel.image.collectAsStateWithLifecycle()
////    val counter by viewModel.counter.collectAsStateWithLifecycle()
//
//    Column {
////        Button(onClick = { getImageFromClipboard()?.let { image = it } }) {
//        Button(onClick = viewModel::getImageFromClipboard ) {
//            Text("Wklej obraz ze schowka")
//        }
//
////        Text("Counter: $counter")
////        Button(onClick = viewModel::incrementCounter) {
////            Text("Increment Counter")
//////        }
////        Button(onClick = { viewModel.incrementCounter() }) {
////            Text(text = "Zwiększ licznik")
////        }
//
//
//        if (image != null) {
//            Image(image!!.toComposeImageBitmap(), contentDescription = "Obraz ze schowka")
//
//            Button(onClick = {
//                // Zmiana rozmiaru i zapis
////                val resizedImage = resizeBufferedImage(image!!, 1080, 608)
////                saveImageToFile(resizedImage, "resized_image.png")
//            }) {
//                Text("Zapisz obrazek 1080x608")
//            }
//        } else {
//            Text("Brak obrazu w schowku")
//        }
//    }
//}
//
//fun saveImageToFile(image: BufferedImage, fileName: String) {
//    val outputFile = File(fileName)
//    ImageIO.write(image, "png", outputFile)
//}
