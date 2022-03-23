package org.mostlylikeable.gradle.compile

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.kotlin.dsl.dependencies
import org.mostlylikeable.gradle.kotlin.dsl.*

class LombokPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        apply<JavaBasePlugin>()

        val lombokLib = "org.projectlombok:lombok:1.18.22"
        dependencies {
            annotationProcessor(lombokLib)
            compileOnly(lombokLib)

            testAnnotationProcessor(lombokLib)
            testCompileOnly(lombokLib)
        }
    }
}
