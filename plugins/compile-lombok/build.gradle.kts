plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
}

dependencies {
    implementation("$group:gradle-kotlin-dsl-ext:$version")
}

gradlePlugin {
    plugins {
        create("compile-lombok") {
            id = "mostlylikeable.compile-lombok"
            implementationClass = "org.mostlylikeable.gradle.compile.LombokPlugin"
        }
    }
}
