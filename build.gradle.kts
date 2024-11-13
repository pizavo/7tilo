plugins {
    kotlin("jvm") version "2.0.20"
}

group = "cz.pizavo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.oshai:kotlin-logging:7.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
