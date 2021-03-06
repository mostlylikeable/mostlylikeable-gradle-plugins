package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class FunctionalTestSpockPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        apply<SpockPlugin>()
        apply<FunctionalTestBasePlugin>()
    }
}
