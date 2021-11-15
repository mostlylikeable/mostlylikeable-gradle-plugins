plugins {
    id("build.gradle-plugin")
}

gradlePlugin {
    plugins {
        create("configure-repositories") {
            id = "mostlylikeable.configure-repositories"
            implementationClass = "org.mostlylikeable.gradle.ConfigureRepositoriesPlugin"
        }
    }
}
