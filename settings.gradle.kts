pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "mostlylikeable.java") {
                useModule("mostlylikeable.java:mostlylikeable-java")
            }
        }
    }
}

rootProject.name = "mostlylikeable-gradle-plugins"

include(":examples:java-plugin")

includeBuild("mostlylikeable-gradle-java")
