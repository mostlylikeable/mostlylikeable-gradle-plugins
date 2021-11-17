plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
    id("mostlylikeable.functional-test-base")
    id("mostlylikeable.integration-test-base")
    id("mostlylikeable.test-spock")
}

dependencies {
    api("mostlylikeable.gradle:mostlylikeable.functional-test-base:$version")
    api("mostlylikeable.gradle:mostlylikeable.test-spock:$version")

    integrationTestImplementation("mostlylikeable.gradle:gradle-plugin-test-spock:$version")
    functionalTestImplementation("mostlylikeable.gradle:gradle-plugin-test-spock:$version")
}

gradlePlugin {
    testSourceSets(sourceSets["functionalTest"])
    plugins {
        create("functional-test-spock") {
            id = "mostlylikeable.functional-test-spock"
            implementationClass = "org.mostlylikeable.gradle.test.FunctionalTestSpockPlugin"
        }
    }
}

