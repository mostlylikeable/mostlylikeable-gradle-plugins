plugins {
    `kotlin-dsl`
    groovy
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
//    implementation(kotlin("gradle-plugin")) // TODO does this do anything?
    implementation("org.gradle.kotlin.kotlin-dsl:org.gradle.kotlin.kotlin-dsl.gradle.plugin:2.1.6") {
        because("so precompiled script can apply the plugin")
    }
    // TODO: will adding the "kotlin-convention" allow not using java.let in precompiled plugins?
}
