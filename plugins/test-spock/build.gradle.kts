plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
}

dependencies {
    implementation("mostlylikeable.gradle:gradle-kotlin-dsl-ext:$version")
}

gradlePlugin {
    plugins {
        create("test-spock") {
            id = "mostlylikeable.test-spock"
            implementationClass = "org.mostlylikeable.gradle.test.SpockPlugin"
        }
    }
}
