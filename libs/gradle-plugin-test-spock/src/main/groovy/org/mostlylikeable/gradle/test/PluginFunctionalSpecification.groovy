package org.mostlylikeable.gradle.test

import org.mostlylikeable.gradle.testkit.EnhancedGradleRunner
import spock.lang.Specification
import spock.lang.TempDir

abstract class PluginFunctionalSpecification extends Specification {

    static final String PLUGINS_TASK = "plugins"
    static final List<String> DEFAULT_ARGS = ["--stacktrace"].asUnmodifiable()

    final String projectName = createProjectName()

    @TempDir File testProjectDir
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
                
                tasks.register<DefaultTask>("$PLUGINS_TASK") {
                    project.plugins
                        .map { it.toString() }
                        .sorted()
                        .forEach { println(it) }
                }
            """.stripIndent()
            return it
        }

        logTestInfo()
    }

    def "plugin can be applied"() {
        when:
        runner(gradleVersion).build()

        then:
        noExceptionThrown()

        where:
        gradleVersion << gradleVersionsToTest
    }

    /**
     * The Test project name.
     *
     * @return the project name
     */
    protected String createProjectName() {
        return "${toKebabLowerCase(this.class.simpleName)}-${System.currentTimeSeconds()}"
    }

    /**
     * Logs some information about each test that is run.<p>
     *
     * <i>Default is to log the path to the build files.</i>
     */
    protected void logTestInfo() {
        println """
            ${settingsFile.name}: ${settingsFile}
            ${buildFile.name}: ${buildFile}
        """.stripIndent()
    }

    /**
     * List of Gradle versions to run tests against.
     *
     * @return the gradle versions
     */
    protected List<String> getGradleVersionsToTest() {
        return ["7.2"]
    }

    /**
     * Gets a {@code GradleRunner} configured with default settings.
     *
     * @param gradleVersion the gradle version
     * @param args arguments to the gradle command
     * @return a runner
     */
    EnhancedGradleRunner runner(String gradleVersion, String... args) {
        return EnhancedGradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(testProjectDir)
            .withArguments(args.toList() + defaultArgs)
            .withPluginClasspath()
            .forwardOutput()
    }

    /**
     * Default arguments to use with the {@code GradleRunner}.<p>
     * @return the list of arguments.
     */
    protected List<String> getDefaultArgs() {
        return DEFAULT_ARGS
    }

    private static String toKebabLowerCase(String s) {
        String kebab = s.replaceAll("([a-z])([A-Z]+)", "\$1-\$2")
        return kebab.toLowerCase()
    }
}
