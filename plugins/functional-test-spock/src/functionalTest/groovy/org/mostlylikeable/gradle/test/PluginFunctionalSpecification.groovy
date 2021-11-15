package org.mostlylikeable.gradle.test

import org.gradle.internal.impldep.org.junit.Rule
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.mostlylikeable.gradle.testkit.EnhancedGradleRunner
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.TempDir

abstract class PluginFunctionalSpecification extends Specification {

    static List<String> DEFAULT_ARGS = ["--stacktrace"]

    final String projectName = createProjectName()

    @TempDir File testProjectDir
//    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile
    File settingsFile

    abstract String getPluginId()

    def setup() {
        settingsFile = new File(testProjectDir, "settings.gradle.kts").with {
            text = """
                rootProject.name = "$projectName"
            """.stripIndent()
            return it
        }
        buildFile = new File(testProjectDir, "build.gradle.kts").with {
            text = """
                plugins {
                    id("$pluginId")
                }
            """.stripIndent()
            return it
        }

        logTestInfo()
    }

//    @Ignore
    def "plugin can be applied"() {
        when:
        runner(gradleVersion).build()

        then:
        noExceptionThrown()

        where:
        gradleVersion << gradleVersionsToTest
    }

    protected List<String> getGradleVersionsToTest() {
        return ["7.2"]
    }

    protected void logTestInfo() {
        println """
            ${settingsFile.name}: ${settingsFile}
            ${buildFile.name}: ${buildFile}
        """.stripIndent()
    }

    protected String createProjectName() {
        return "${toKebabLowerCase(this.class.simpleName)}-${System.currentTimeSeconds()}"
    }

    EnhancedGradleRunner runner(String gradleVersion, String... args) {
        return EnhancedGradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(testProjectDir)
            .withArguments(args.toList() + defaultArgs)
            .withPluginClasspath()
            .forwardOutput()
    }

    protected List<String> getDefaultArgs() {
        return DEFAULT_ARGS
    }

    private static String toKebabLowerCase(String s) {
        String kebab = s.replaceAll("([a-z])([A-Z]+)", "\$1-\$2")
        return kebab.toLowerCase()
    }
}
