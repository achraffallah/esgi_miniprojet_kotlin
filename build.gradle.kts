plugins {
    kotlin("jvm") version "2.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("bibliotheque.presentation.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

// Permet de lire le clavier avec `gradle run` (menu interactif)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
