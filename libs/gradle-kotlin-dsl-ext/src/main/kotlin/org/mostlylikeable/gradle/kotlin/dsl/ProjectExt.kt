package org.mostlylikeable.gradle.kotlin.dsl

import org.gradle.api.Plugin
import org.gradle.api.Project

inline fun <reified T : Plugin<*>> Project.apply() {
    pluginManager.apply(T::class.java)
}

fun Project.hasTask(name: String): Boolean {
    return tasks.findByName(name) != null
}
