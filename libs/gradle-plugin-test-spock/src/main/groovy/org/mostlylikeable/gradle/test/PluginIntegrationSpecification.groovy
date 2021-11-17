package org.mostlylikeable.gradle.test

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import spock.lang.TempDir

abstract class PluginIntegrationSpecification extends Specification {

    @TempDir File testProjectDir

    abstract String getPluginId()

    def "plugin can be applied"() {
        setup:
        Project project = buildProject()

        when:
        project.pluginManager.apply(pluginId)

        then:
        project.plugins.hasPlugin(pluginId)
    }

    protected Project buildProject() {
        return projectBuilder.build()
    }

    protected ProjectBuilder getProjectBuilder() {
        return ProjectBuilder.builder()
            .withProjectDir(testProjectDir)
    }
}
