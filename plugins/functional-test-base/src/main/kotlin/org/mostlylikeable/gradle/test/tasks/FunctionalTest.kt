package org.mostlylikeable.gradle.test.tasks

import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent

open class FunctionalTest : Test() {

    init {
        description = "Runs the functional tests."
        group = "verification"
        testLogging.events(
            TestLogEvent.STARTED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_ERROR,
            TestLogEvent.STANDARD_OUT
        )
    }

    fun withSourceSet(testSourceSet: SourceSet) {
        testClassesDirs = testSourceSet.output.classesDirs
        classpath = testSourceSet.runtimeClasspath
    }
}
