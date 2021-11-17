import java.util.Properties

plugins {
    id("org.gradle.kotlin.kotlin-dsl")
    `java-library`
    groovy
}

val props = Properties().let {
    it.load(file("../../gradle.properties").inputStream())
    group = it["group"] as String
    version = it["version"] as String
}

java.run {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}

repositories {
    mavenCentral()
}
