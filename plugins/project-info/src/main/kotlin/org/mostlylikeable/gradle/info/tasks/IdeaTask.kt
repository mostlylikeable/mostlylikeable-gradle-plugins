package org.mostlylikeable.gradle.info.tasks

import org.gradle.plugins.ide.idea.IdeaPlugin
import java.io.File

open class IdeaTask : ProjectInfoTask() {

    init {
        description = "Displays IdeaPlugin configuration declared in project ':${project.name}'."
        title = "IdeaPlugin info for project ':${project.name}'"
    }

    override fun populateInfo() {
        // TODO: should be able to use withType but Closure vs Action methods cause issues
        val idea = project.plugins.findPlugin(IdeaPlugin::class.java)
        if (idea != null) {
            idea.model.module.apply {
                section("Source Dirs") { sourceDirs.toRelativePaths().forEach { line(it) } }
                section("Test Source Dirs") { testSourceDirs.toRelativePaths().forEach { line(it) } }

                section("Resource Dirs") { resourceDirs.toRelativePaths().forEach { line(it) } }
                section("Test Resource Dirs") { testResourceDirs.toRelativePaths().forEach { line(it) } }

                section("Generated Source Dirs") { generatedSourceDirs.toRelativePaths().forEach { line(it) } }
                section("Exclude Source Dirs") { excludeDirs.toRelativePaths().forEach { line(it) } }

                section("Scopes") {
                    // scope looks like: [COMPILE: [plus: [], minus: []], TEST: [plus: [], minus: []]]
                    scopes.forEach { (scope, subScopes) ->
                        subScopes.forEach { (plusMinus, configs) ->
                            configs.forEach { line("$scope.$plusMinus - ${it.name}") }
                        }
                    }
                }
            }
        } else {
            error("${IdeaPlugin::class.java.simpleName} is not installed.")
        }
    }

    private fun Set<File>.toRelativePaths(): List<String> {
        return map { it.path.removePrefix(project.rootDir.path) }
    }
}
