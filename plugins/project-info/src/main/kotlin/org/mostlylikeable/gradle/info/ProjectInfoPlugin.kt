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
        tasks.register<PluginsTask>(PLUGIN_TASK_NAME)
        tasks.register<RepositoriesTask>(REPOSITORY_TASK_NAME)
        tasks.register<ConfigurationsTask>(CONFIGURATION_TASK_NAME)
        tasks.register<IdeaTask>(IDEA_TASK_NAME)
    }

    companion object {
        const val CONFIGURATION_TASK_NAME = "configurations"
        const val PLUGIN_TASK_NAME = "plugins"
        const val REPOSITORY_TASK_NAME = "repositories"
        const val IDEA_TASK_NAME = "ideaInfo"
    }
}
