package org.mostlylikeable.gradle.kotlin.dsl

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.annotationProcessor(dependency: String)
    : Unit = addInternal("annotationProcessor", dependency)

fun DependencyHandler.annotationProcessor(dependency: String, configuration: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("annotationProcessor", dependency, configuration)

fun DependencyHandler.compileOnly(dependency: String)
    : Unit = addInternal("compileOnly", dependency)

fun DependencyHandler.compileOnly(dependency: String, action: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("compileOnly", dependency, action)

fun DependencyHandler.implementation(dependency: String)
    : Unit = addInternal("implementation", dependency)

fun DependencyHandler.implementation(dependency: String, action: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("implementation", dependency, action)

fun DependencyHandler.testAnnotationProcessor(dependency: String)
    : Unit = addInternal("testAnnotationProcessor", dependency)

fun DependencyHandler.testAnnotationProcessor(dependency: String, action: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("testAnnotationProcessor", dependency, action)

fun DependencyHandler.testCompileOnly(dependency: String)
    : Unit = addInternal("testCompileOnly", dependency)

fun DependencyHandler.testCompileOnly(dependency: String, action: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("testCompileOnly", dependency, action)

fun DependencyHandler.testImplementation(dependency: String)
    : Unit = addInternal("testImplementation", dependency)

fun DependencyHandler.testImplementation(dependency: String, configuration: ExternalModuleDependency.() -> Unit)
    : Unit = addInternal("testImplementation", dependency, configuration)

private fun DependencyHandler.addInternal(
    configurationName: String,
    dependencyNotation: String,
    configuration: (ExternalModuleDependency.() -> Unit)? = null
) {
    add(configurationName, dependencyNotation)
        .apply { configuration?.invoke(this as ExternalModuleDependency) }
}
