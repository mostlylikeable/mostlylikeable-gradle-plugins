package org.mostlylikeable.gradle.info.tasks

open class PluginsTask : ProjectInfoTask() {

    // parse group package and class name from "org.gradle.api.plugins.JavaBasePlugin@12345678"
    private val pluginRegex = Regex("""^(.*)\.(\w*).*""")
    private val precompiledRegex = Regex("""(\w*).*""")
    private val gradlePlugins = Section("Gradle Plugins")
    private val otherPlugins = Section("Other Plugins")

    init {
        description = "Displays all plugins declared in project ':${project.name}'."
        title = "Plugins applied to project ':${project.name}'"
        addSections(gradlePlugins, otherPlugins)
    }

    override fun populateInfo() {
        project.plugins.asSequence()
            .map { splitPackageAndPlugin(it.toString()) }
            .groupBy({ it[0] }, { it[1] }) // map plugins to package
            .forEach { (pkg, plugins) ->
                val section = if (pkg.startsWith("org.gradle.")) gradlePlugins else otherPlugins
                plugins.sorted().forEach { section.line(it) }
            }
    }

    /**
     * Returns either `[package, pluginClass]` or `["precompiled", pluginClass]`
     */
    private fun splitPackageAndPlugin(plugin: String): List<String> {
        return if (plugin.contains(".")) {
            pluginRegex.find(plugin)!!.destructured.toList()
        } else { listOf("precompiled", precompiledRegex.find(plugin)!!.destructured.component1()) }
    }
}
