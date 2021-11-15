package org.mostlylikeable.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigureRepositoriesPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        repositories.run {
            add(mavenCentral())
            add(google())
            add(gradlePluginPortal())
        }
    }
}
