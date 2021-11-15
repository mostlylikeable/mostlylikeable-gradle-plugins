package org.mostlylikeable.gradle.kotlin.dsl

import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer

val SourceSetContainer.main: SourceSet
    get() = getByName("main")

val SourceSetContainer.test: SourceSet
    get() = getByName("test")
