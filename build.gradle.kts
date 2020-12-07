plugins {
    kotlin("jvm") version "1.4.20"
}

group = "xyz.davidsimon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}
