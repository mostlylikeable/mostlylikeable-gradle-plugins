package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude
import org.mostlylikeable.gradle.kotlin.dsl.apply

class SpockPlugin : Plugin<Project> {

    val configurationName = "testImplementation"

    override fun apply(project: Project): Unit = project.run {
        apply<GroovyPlugin>()
        apply<JUnitPlugin>()

        // TODO: look into https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html#providing_default_dependencies_for_plugins
        dependencies {
            configurationName("org.spockframework:spock-core:2.0-groovy-3.0")
                .let { it as ExternalModuleDependency }
                .exclude(group = "org.codehaus.groovy")
        }
    }
}
