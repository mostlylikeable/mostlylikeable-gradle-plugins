package org.mostlylikeable.gradle.test

class FunctionalTestSpockPluginFuncSpec extends PluginFunctionalSpecification {

    String pluginId = "mostlylikeable.functional-test-spock"

    def "additional plugins are applied"() {
        when:
        def result = runner(gradleVersion)
            .withArguments("plugins")
            .build()

        then:
        result.assertOutput(
            "org.gradle.api.plugins.GroovyPlugin",
            "org.gradle.api.plugins.JavaPlugin",
            "org.gradle.plugins.ide.idea.IdeaPlugin",
            "org.mostlylikeable.gradle.test.FunctionalTestBasePlugin",
            "org.mostlylikeable.gradle.test.FunctionalTestSpockPlugin",
            "org.mostlylikeable.gradle.test.SpockPlugin",
        )

        where:
        gradleVersion << gradleVersionsToTest
    }
}
