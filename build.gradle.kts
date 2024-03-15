plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.microutils:kotlin-logging:1.11.0")
    implementation("org.slf4j:slf4j-simple:1.7.32")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}