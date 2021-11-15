package org.mostlylikeable.gradle.info.tasks

import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

open class RepositoriesTask : ProjectInfoTask() {

    // don't sort these so lookup order is preserved
    private val mavenSection = Section("Maven Repositories", false)
    private val ivySection = Section("Ivy Repositories", false)
    private val otherSection = Section("Other Repositories", false)

    init {
        description = "Displays all repositories declared in project ':${project.name}'."
        title = "Repositories configured for project ':${project.name}'"
        addSections(mavenSection, ivySection, otherSection)
    }

    override fun populateInfo() {
        project.repositories
            .forEach {
                when(it) {
                    is MavenArtifactRepository -> mavenSection.line(it.url.toString())
                    is IvyArtifactRepository -> ivySection.line(it.url.toString())
                    else -> otherSection.line(it.name)
                }
            }
    }
}
