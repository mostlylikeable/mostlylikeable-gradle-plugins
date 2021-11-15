package org.mostlylikeable.gradle.info.tasks

open class ConfigurationsTask : ProjectInfoTask() {

    private val resolvableSection = Section("Resolvable configurations")
    private val otherSection = Section("Other configurations")
    private val dependencySection = Section("Resolved Dependencies")
    private val artifactSection = Section("Artifacts")

    init {
        description = "Displays all configurations declared in project ':${project.name}'."
        title = "Configurations for project ':${project.name}'"
        addSections(
            resolvableSection,
            otherSection,
            artifactSection,
            dependencySection
        )
    }

    override fun populateInfo() {
        project.configurations.asSequence()
            .sortedBy { it.name }
            .onEach { (if (it.isCanBeResolved) resolvableSection else otherSection).line(it.name) }
            .onEach { c -> c.artifacts.forEach { artifactSection.line("${c.name} - ${it.file.name}") } }
            .filter { it.isCanBeResolved }
            .forEach { c ->
                c.resolvedConfiguration.firstLevelModuleDependencies
                    .forEach { dependencySection.line("${c.name} - ${it.module.id}") }
            }
    }
}
