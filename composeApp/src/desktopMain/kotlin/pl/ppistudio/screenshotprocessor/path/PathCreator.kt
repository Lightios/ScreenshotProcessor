package pl.ppistudio.screenshotprocessor.path

import pl.ppistudio.screenshotprocessor.model.Ability

fun createPath(
    ability: Ability,
    additionalOption: String,
    map: String,
    agent: String = "",
    fileName: String = ""
): String {
    val folder = ability.folder


//    var path = "$ABSOLUTE_PATH/$folder/$additionalOption/$map"
    var path = "$ABSOLUTE_PATH/$folder"

    if (folder == "Setups") {
        path += "/$agent"
    } else {
        path += "/$additionalOption"
    }

    path += "/$map"

    if (fileName != "")
        path += "/$fileName.jpg"

    return path
}