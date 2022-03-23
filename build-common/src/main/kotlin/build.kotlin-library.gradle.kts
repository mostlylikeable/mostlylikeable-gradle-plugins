import org.gradle.kotlin.dsl.repositories
import java.util.Properties

plugins {
    id("org.gradle.kotlin.kotlin-dsl")
    `java-library`
}

val props = Properties().let {
    it.load(file("../../gradle.properties").inputStream())
    group = it["group"] as String
    version = it["version"] as String
}

java.run {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
}

repositories {
    mavenCentral()
}
