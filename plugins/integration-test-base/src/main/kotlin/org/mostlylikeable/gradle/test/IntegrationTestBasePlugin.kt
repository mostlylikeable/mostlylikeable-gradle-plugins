package org.mostlylikeable.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.ide.idea.IdeaPlugin

class IntegrationTestBasePlugin : Plugin<Project> {

    // TODO: figure out a way to share this logic...
    override fun apply(project: Project): Unit = project.run {
        val prefix = "integrationTest"
        val sourceSets = the<JavaPluginExtension>().sourceSets
        val integrationTest by sourceSets.creating
        integrationTest.apply {
            compileClasspath += sourceSets["main"]!!.output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath
        }

        val integrationTestTask = tasks.register<Test>(prefix) {
            description = "Runs the integration tests."
            group = "verification"
            useJUnitPlatform()

            testClassesDirs = integrationTest.output.classesDirs
            classpath = integrationTest.runtimeClasspath

            testLogging.apply {
                events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
            }

            shouldRunAfter("test")
        }

        tasks["check"].dependsOn(integrationTestTask)

        plugins.apply(IdeaPlugin::class.java)
        afterEvaluate {
            // TODO: should be able to use withType but it runs into issues with Closure vs Action methods
            plugins.getPlugin(IdeaPlugin::class.java).also { idea ->
                idea.model.module.apply {
                    // configure intellij so it correctly identifies test sources
                    testSourceDirs = testSourceDirs +
                        integrationTest.allSource.srcDirs -
                        integrationTest.resources.srcDirs
                    testResourceDirs = testResourceDirs + integrationTest.resources.srcDirs
                    scopes["TEST"]!!["plus"]!!.addAll(listOf(
                        project.configurations["${prefix}CompileClasspath"],
                        project.configurations["${prefix}RuntimeClasspath"]
                    ))
                }
            }
        }
    }
}
