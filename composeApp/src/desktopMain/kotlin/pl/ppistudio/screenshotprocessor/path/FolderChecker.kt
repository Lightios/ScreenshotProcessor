package pl.ppistudio.screenshotprocessor.path

import java.io.File
import java.io.IOException

fun createFoldersIfNotExist(path: String) {
    val file = File(ABSOLUTE_PATH)
    if (!file.exists()) {
        throw IOException("Folder $ABSOLUTE_PATH does not exist")
    }

    var currentPath = "$ABSOLUTE_PATH/"
    var rest = path.removePrefix(currentPath)

    var folders = rest.split("/")
    folders = folders.dropLast(1)

    folders.forEach { folder ->
        currentPath += "$folder/"
        val file = File(currentPath)
        if (!file.exists()) {
            file.mkdir()
        }
    }
}


fun main() {
    createFoldersIfNotExist("C:/dummyFolder/Effects/Bind/Ascent/1")
}