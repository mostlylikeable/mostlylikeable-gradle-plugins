package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude
import org.mostlylikeable.gradle.kotlin.dsl.apply
import org.mostlylikeable.gradle.kotlin.dsl.testImplementation

class SpockPlugin : Plugin<Project> {

    val configurationName = "testImplementation"

    override fun apply(project: Project): Unit = project.run {
        apply<GroovyPlugin>()

//        configurations.withType<Test>

        dependencies {
            configurationName("org.spockframework:spock-core:2.0-groovy-3.0")
                .let { it as ExternalModuleDependency }
                .exclude(group = "org.codehaus.groovy")
            configurationName("org.junit.jupiter:junit-jupiter:5.7.1")
        }
    }
}
