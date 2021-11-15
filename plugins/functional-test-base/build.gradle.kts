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
        create("functional-test-base") {
            id = "mostlylikeable.functional-test-base"
            implementationClass = "org.mostlylikeable.gradle.test.FunctionalTestBasePlugin"
        }
    }
}
