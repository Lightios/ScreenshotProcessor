package pl.ppistudio.screenshotprocessor.path

import java.awt.Desktop
import java.io.File
import java.io.IOException

fun openExplorerAtPath(path: String) {
    val file = File(path)
    if (file.exists()) {
        try {
            Desktop.getDesktop().open(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } else {
        println("The specified path does not exist.")
    }
}

fun main() {
    openExplorerAtPath("C:\\Users\\nukin\\Desktop")

}