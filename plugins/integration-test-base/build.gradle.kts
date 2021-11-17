plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
}

gradlePlugin {
    plugins {
        create("integration-test-base") {
            id = "mostlylikeable.integration-test-base"
            implementationClass = "org.mostlylikeable.gradle.test.IntegrationTestBasePlugin"
        }
    }
}
