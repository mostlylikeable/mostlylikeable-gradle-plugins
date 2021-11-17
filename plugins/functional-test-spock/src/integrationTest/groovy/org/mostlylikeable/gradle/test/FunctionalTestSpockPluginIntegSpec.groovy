package org.mostlylikeable.gradle.test

import org.gradle.api.Project

class FunctionalTestSpockPluginIntegSpec extends PluginIntegrationSpecification {

    String pluginId = "mostlylikeable.functional-test-spock"

    def "additional plugins are applied"() {
        when:
        Project p = buildProject().with {
            pluginManager.apply(pluginId)
            return it
        }

        then:
        p.plugins.hasPlugin(FunctionalTestBasePlugin)
        p.plugins.hasPlugin(FunctionalTestSpockPlugin)
        p.plugins.hasPlugin(SpockPlugin)
    }
}
