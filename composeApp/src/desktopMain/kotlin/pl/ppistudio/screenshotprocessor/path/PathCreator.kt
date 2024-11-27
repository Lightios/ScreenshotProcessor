package pl.ppistudio.screenshotprocessor.path

import pl.ppistudio.screenshotprocessor.model.Ability

fun createPath(
    ability: Ability,
    additionalOption: String,
    map: String,
    fileName: String
): String {
    val folder = ability.folder


    return "$ABSOLUTE_PATH/$folder/$additionalOption/$map/$fileName.jpg"
}