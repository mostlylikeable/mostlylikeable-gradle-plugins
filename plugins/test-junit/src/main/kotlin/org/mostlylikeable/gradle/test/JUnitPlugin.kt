package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.kotlin.dsl.*
import org.mostlylikeable.gradle.kotlin.dsl.apply

class JUnitPlugin : Plugin<Project> {

    val configurationName = "testImplementation"

    override fun apply(project: Project): Unit = project.run {
        apply<JavaBasePlugin>()

        dependencies {
            configurationName("org.junit.jupiter:junit-jupiter:5.7.1")
        }
    }
}
