rootProject.name = "mostlylikeable-gradle-plugins"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("mostlylikeable.")) {
                useModule("mostlylikeable.gradle:${requested.id.id}:${requested.version}")
            }
        }
    }
}

includeBuild("build-common")

fun includeLibraryBuild(name: String) {
    includeBuild("libs/$name") {
        dependencySubstitution {
            substitute(module("mostlylikeable.gradle:$name"))
                .using(project(":"))
        }
    }
}

fun includePluginBuild(name: String) {
    // substitute the lib so other plugins can include in dependencies, so they can apply this one
    includeBuild("plugins/$name") {
        dependencySubstitution {
//            substitute(module("mostlylikeable.gradle:mostlylikeable.$name"))
            substitute(module("mostlylikeable.gradle:mostlylikeable.$name"))
                .using(project(":"))
//            substitute(module("mostlylikeable.gradle:$name"))
//                .using(project(":"))
        }
    }
}

// TODO: iterate through layout and include these dynamically
// libs
includeLibraryBuild("gradle-kotlin-dsl-ext")
includeLibraryBuild("gradle-testkit-ext")

// NOTE: the order these are declared matters. plugins with dependencies should be declared after the plugin they
//   depend on or else they may/will have to also includeBuild for the project they depend on.
// common-plugins
includePluginBuild("configure-repositories")
includePluginBuild("project-info")

// plugins
includePluginBuild("test-spock")

includePluginBuild("integration-test-base")

includePluginBuild("functional-test-base")
includePluginBuild("functional-test-spock")
