import org.gradle.internal.impldep.org.apache.ivy.core.IvyPatternHelper.substitute

rootProject.name = "mostlylikeable-gradle-plugins"

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
    // substitute the lib so other plugins can include in dependencies and apply it
    includeBuild("plugins/$name") {
        dependencySubstitution {
            substitute(module("mostlylikeable.gradle:mostlylikeable.$name"))
                .using(project(":"))
        }
    }
}

// TODO: iterate through layout and include these dynamically
// libs
includeLibraryBuild("gradle-kotlin-dsl-ext")
includeLibraryBuild("gradle-plugin-test-junit")
includeLibraryBuild("gradle-plugin-test-spock")
includeLibraryBuild("gradle-testkit-ext")

// NOTE: the order these are declared matters. plugins with dependencies should be declared after the plugin they
//   depend on or else they may/will have to also includeBuild for the project they depend on.
// common-plugins
includePluginBuild("configure-repositories")
includePluginBuild("project-info")

// plugins
includePluginBuild("compile-lombok")

includePluginBuild("test-junit")
includePluginBuild("test-spock")

includePluginBuild("integration-test-base")

includePluginBuild("functional-test-base")
includePluginBuild("functional-test-spock")
