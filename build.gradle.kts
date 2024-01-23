import java.io.ByteArrayOutputStream
import java.util.*

plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.timiaregbesola"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.15.3")
    testImplementation(kotlin("test"))
}

val algoList = listOf("Map", "InsertionSort", "PrimsList", "BFSGraphList", "LinearSearch", "TwoCrystalBalls", "Trie", "Stack", "LRU")

tasks.test {
    useJUnitPlatform()
}

tasks.register("runGenerator", JavaExec::class) {
    group = "kotlinpoet"
    classpath = sourceSets["main"].runtimeClasspath

    mainClass.set("scripts.DsaKt")

    args = algoList

    standardOutput = ByteArrayOutputStream()

    val outputDir = File("${buildDir}/gensources")


    doLast {
        val regex = "(----)(\\w+)(----\n)"
        val matches = Regex(regex).findAll(standardOutput.toString())
        val names = matches.map { it.groupValues[2] }.toList()

        standardOutput.toString()
            .replace("public ", "")
            .split(regex.toRegex())
            .forEachIndexed { index, s ->
                if (s.isNotEmpty()) {
                    val outputFile = File(
                        outputDir,
                        "${names[index - 1].replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                        }}.kt"
                    )
                    if (!outputDir.exists()) {
                        outputDir.mkdirs()
                    }
                    outputFile.writeText(s)
                }
        }
    }
}


kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}