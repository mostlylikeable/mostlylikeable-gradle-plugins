plugins {
    id("build.gradle-plugin")
    id("mostlylikeable.configure-repositories")
    id("mostlylikeable.project-info")
    id("mostlylikeable.functional-test-base")
    id("mostlylikeable.integration-test-base")
    id("mostlylikeable.test-spock")
}

dependencies {
    api("$group:mostlylikeable.functional-test-base:$version")
    api("$group:mostlylikeable.test-spock:$version")

    integrationTestImplementation("$group:gradle-plugin-test-spock:$version")
    functionalTestImplementation("$group:gradle-plugin-test-spock:$version")
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

