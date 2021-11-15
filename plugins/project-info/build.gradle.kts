plugins {
    id("build.gradle-plugin")
}

gradlePlugin {
    plugins {
        create("project-info") {
            id = "mostlylikeable.project-info"
            implementationClass = "org.mostlylikeable.gradle.info.ProjectInfoPlugin"
        }
    }
}
