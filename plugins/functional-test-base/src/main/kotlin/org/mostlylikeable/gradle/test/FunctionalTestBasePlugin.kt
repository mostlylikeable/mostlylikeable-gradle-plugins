package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.mostlylikeable.gradle.kotlin.dsl.hasTask

class FunctionalTestBasePlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        val prefix = "functionalTest"
        val sourceSets = the<JavaPluginExtension>().sourceSets
        val functionalTest by sourceSets.creating
        configurations[functionalTest.implementationConfigurationName]
            .extendsFrom(configurations["testImplementation"])
        configurations[functionalTest.runtimeOnlyConfigurationName]
            .extendsFrom(configurations["testRuntimeOnly"])

        val functionalTestTask = tasks.register<Test>(prefix) {
            description = "Runs the functional tests."
            group = "verification"
            useJUnitPlatform()

            testClassesDirs = functionalTest.output.classesDirs
            classpath = configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output

            testLogging.apply {
                events(PASSED, FAILED, SKIPPED)
            }

            if (project.hasTask("integrationTest")) {
                shouldRunAfter("test", "integrationTest")
            } else {
                shouldRunAfter("test")
            }
        }

        tasks["check"].dependsOn(functionalTestTask)

        plugins.apply(IdeaPlugin::class.java)
        afterEvaluate {
            // TODO: should be able to use withType but it runs into issues with Closure vs Action methods
            plugins.getPlugin(IdeaPlugin::class.java).also { idea ->
                idea.model.module.apply {
                    // configure intellij so it correctly identifies test sources
                    testSourceDirs = testSourceDirs +
                        functionalTest.allSource.srcDirs -
                        functionalTest.resources.srcDirs
                    testResourceDirs = testResourceDirs + functionalTest.resources.srcDirs
                    scopes["TEST"]!!["plus"]!!.addAll(listOf(
                        project.configurations["${prefix}CompileClasspath"],
                        project.configurations["${prefix}RuntimeClasspath"]
                    ))
                }
            }
        }
    }
}
