package org.mostlylikeable.gradle.test

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.mostlylikeable.gradle.test.FunctionalTestSpockPlugin
import spock.lang.Specification

class FunctionalTestSpockPluginSpec extends Specification {

    def "foo"() {
        given:
        Project p = ProjectBuilder.builder().build()

        when:
        p.pluginManager.with {
            it.apply(JavaPlugin)
            it.apply(FunctionalTestSpockPlugin)
        }

        then:
        p.plugins.hasPlugin(FunctionalTestSpockPlugin)
    }
}
