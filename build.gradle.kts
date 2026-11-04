plugins {
    kotlin("jvm") version "2.3.10"
    application
}

group = "de.thws.fiw.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("de.thws.fiw.kotlin.portfolio1.MainKt")
}