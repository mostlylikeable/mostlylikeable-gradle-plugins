pluginManagement {
    // TODO: can this be in build-common or gradle and applied from there via plugin?
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("mostlylikeable.")) {
                useModule("mostlylikeable.gradle:${requested.id.id}:${requested.version}")
            }
        }
    }
}

rootProject.name = "functional-test-spock"
