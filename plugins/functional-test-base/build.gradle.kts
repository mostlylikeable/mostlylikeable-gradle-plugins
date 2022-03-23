plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
    id("mostlylikeable.integration-test-base")
    id("mostlylikeable.test-junit")
}

dependencies {
    implementation("$group:gradle-kotlin-dsl-ext:$version")

    integrationTestImplementation("$group:gradle-plugin-test-junit:$version")
}

gradlePlugin {
    plugins {
        create("functional-test-base") {
            id = "mostlylikeable.functional-test-base"
            implementationClass = "org.mostlylikeable.gradle.test.FunctionalTestBasePlugin"
        }
    }
}
