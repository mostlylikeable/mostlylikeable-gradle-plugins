rootProject.name = "build-common"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            println("resolve: $requested")
            if (requested.id.id.startsWith("mostlylikeable.")) {
                useModule("mostlylikeable.gradle:${requested.id.id}:${requested.version}")
            }
        }
    }
}
