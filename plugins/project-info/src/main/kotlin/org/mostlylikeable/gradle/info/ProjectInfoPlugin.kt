package org.mostlylikeable.gradle.info

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.mostlylikeable.gradle.info.tasks.ConfigurationsTask
import org.mostlylikeable.gradle.info.tasks.IdeaTask
import org.mostlylikeable.gradle.info.tasks.PluginsTask
import org.mostlylikeable.gradle.info.tasks.RepositoriesTask

class ProjectInfoPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        tasks.register<PluginsTask>("pluginInfo")
        tasks.register<RepositoriesTask>("repositoryInfo")
        tasks.register<ConfigurationsTask>("configurationInfo")
        tasks.register<IdeaTask>("ideaInfo")
    }
}
