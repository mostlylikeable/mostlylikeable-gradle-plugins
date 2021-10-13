plugins {
    kotlin("jvm")
    `java-gradle-plugin`
}

group = "mostlylikeable.java"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}")
}

gradlePlugin {
    plugins {
        create("MostlyLikeableJavaPlugin") {
            id = "mostlylikeable-java"
            group = project.group
            implementationClass = "mostlylikeable.java.MostlyLikeableJavaPlugin"
        }
    }
}
