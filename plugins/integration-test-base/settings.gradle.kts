pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("mostlylikeable.")) {
                useModule("mostlylikeable.gradle:${requested.id.id}:${requested.version}")
            }
        }
    }
}

rootProject.name = "integration-test-base"
