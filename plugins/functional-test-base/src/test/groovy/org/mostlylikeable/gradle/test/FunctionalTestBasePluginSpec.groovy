package org.mostlylikeable.gradle.test

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class FunctionalTestBasePluginSpec extends Specification {

    def "foo"() {
        given:
        Project p = ProjectBuilder.builder().build()

        when:
        p.pluginManager.with {
            it.apply(JavaPlugin)
            it.apply(FunctionalTestBasePlugin)
        }

        then:
        p.plugins.hasPlugin(FunctionalTestBasePlugin)
    }
}
