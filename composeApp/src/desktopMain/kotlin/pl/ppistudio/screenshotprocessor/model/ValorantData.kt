package pl.ppistudio.screenshotprocessor.model

val MAPS = listOf(
    "Abyss",
    "Ascent",
    "Bind",
    "Breeze",
    "Fracture",
    "Haven",
    "Icebox",
    "Lotus",
    "Pearl",
    "Split",
    "Sunset",
)

val AGENTS = listOf(
//    "Astra",
//    "Breach",
    "Brimstone",
//    "Chamber",
    "Cypher",
//    "Deadlock",
    "Fade",
    "Gekko",
//    "Harbor",
//    "Iso",
//    "Jett",
    "KAYO",
    "Killjoy",
//    "Neon",
//    "Omen",
//    "Phoenix",
//    "Raze",
//    "Reyna",
    "Sage",
//    "Skye",
    "Sova",
    "Viper",
    "Vyse",
//    "Yoru",
)


val poisonCloud = Ability("Poison Cloud", "VIPERQ", AbilityType.DEFAULT)
val toxicScreen = Ability("Toxic Screen", "VIPERE", AbilityType.DEFAULT)
val snakeBite = Ability("Snake Bite", "", AbilityType.POSTPLANT)
val nanoswarm = Ability("Nanoswarm", "", AbilityType.POSTPLANT)
val alarmbotTurret = Ability("Alarmbot/Turret", "Setups", AbilityType.SETUP)
val haunt = Ability("Haunt", "FADEE", AbilityType.DEFAULT)
val slowOrb = Ability("Slow Orb", "SAGEQ", AbilityType.DEFAULT)
val barrierOrb = Ability("Barrier Orb", "Setups", AbilityType.SETUP)
val shockBolt = Ability("Shock Bolt", "", AbilityType.POSTPLANT)
val reconBolt = Ability("Recon Bolt", "SOVAE", AbilityType.DEFAULT)
val moshpit = Ability("Moshpit", "", AbilityType.POSTPLANT)
val gatecrash = Ability("Gatecrash", "YORUE", AbilityType.DEFAULT)
val cyberCage = Ability("Cyber Cage", "CYPHERQ", AbilityType.DEFAULT)
val trapwireSpycam = Ability("Trapwire/Spycam", "Setups", AbilityType.SETUP)
val fragment = Ability("Fragment", "", AbilityType.POSTPLANT)
val zeropoint = Ability("Zeropoint", "KAYOE", AbilityType.DEFAULT)
val incendiary = Ability("Incendiary", "", AbilityType.POSTPLANT)
val shearArcrose = Ability("Shear/Arc Rose", "Setups", AbilityType.SETUP)

fun agentToAbilities(agent: String): List<Ability> {
    return when (agent) {
        "Viper" -> listOf(poisonCloud, toxicScreen, snakeBite)
        "Killjoy" -> listOf(nanoswarm, alarmbotTurret)
        "Fade" -> listOf(haunt)
        "Sage" -> listOf(slowOrb, barrierOrb)
        "Sova" -> listOf(shockBolt, reconBolt)
        "Gekko" -> listOf(moshpit)
        "Yoru" -> listOf(gatecrash)
        "Cypher" -> listOf(cyberCage, trapwireSpycam)
        "KAYO" -> listOf(fragment, zeropoint)
        "Brimstone" -> listOf(incendiary)
        "Vyse" -> listOf(shearArcrose)
        else -> emptyList()
    }
}
