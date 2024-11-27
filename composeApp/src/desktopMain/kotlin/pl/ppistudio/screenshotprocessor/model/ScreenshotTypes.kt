package pl.ppistudio.screenshotprocessor.model

val SCREENSHOT_TYPES = listOf(
    "Spike Position",
    "Lineup Position",
    "Lineup Target"
)

enum class AbilityType {
    DEFAULT,
    POSTPLANT,
    SETUP
}

fun get(ability: Ability): List<String> {
    return when (ability.type) {
        AbilityType.DEFAULT -> listOf("Effects", "Positions", "Targets")
        AbilityType.POSTPLANT -> listOf("LineupPositions", "LineupTargets", "SpikePositions")
        AbilityType.SETUP ->  listOf("Setup")
    }
}