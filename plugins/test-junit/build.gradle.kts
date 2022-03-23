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
        create("test-spock") {
            id = "mostlylikeable.test-junit"
            implementationClass = "org.mostlylikeable.gradle.test.JUnitPlugin"
        }
    }
}
