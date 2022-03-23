package org.mostlylikeable.gradle.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mostlylikeable.gradle.testkit.EnhancedGradleRunner;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mostlylikeable.gradle.test.IoUtils.appendAndClose;
import static org.mostlylikeable.gradle.test.Strings.multiline;

public abstract class PluginFunctionalTest {

    static final String PLUGINS_TASK = "plugins";
    static final List<String> DEFAULT_ARGS = Stream.of("--stacktrace")
        .collect(Collectors.toUnmodifiableList());

    final String projectName = createProjectName();

    @TempDir File testProjectDir;
    File buildFile;
    File settingsFile;

    abstract String getPluginId();

    @BeforeEach
    void setup() {
        settingsFile = new File(testProjectDir, "settings.gradle.kts");
        appendToSettings("rootProject.name = " + projectName);

        buildFile = new File(testProjectDir, "build.gradle.kts");
        appendToBuild(format(multiline(
            "",
            "plugins {",
            "    id(%s)",
            "}",
            "",
            "tasks.register<DefaultTask>(\"%s\") {",
            "    project.plugins",
            "        .map { it.toString() }",
            "        .sorted()",
            "        .forEach { println(it) }",
            "}"
        ), getPluginId(), PLUGINS_TASK));

        logTestInfo();
    }

    @DisplayName("plugin can be applied")
    @Test
    void testPluginCanBeApplied() {
        assertDoesNotThrow(() -> runner("7.2").build());
    }

    @SneakyThrows
    protected void appendToSettings(String s) {
        appendAndClose(settingsFile, s + "\n");
    }

    @SneakyThrows
    protected void appendToBuild(String s) {
        appendAndClose(buildFile, s + "\n");
    }

    /**
     * The Test project name.
     *
     * @return the project name
     */
    protected String createProjectName() {
        return toKebabLowerCase(this.getClass().getSimpleName()) + "-" + (System.currentTimeMillis() / 1000);
    }

    /**
     * Logs some information about each test that is run.<p>
     *
     * <i>Default is to log the path to the build files.</i>
     */
    protected void logTestInfo() {
        System.out.println(multiline(
            settingsFile.getName() + ": " + settingsFile,
            buildFile.getName() + ": " + buildFile
        ));
    }

    /**
     * List of Gradle versions to run tests against.
     *
     * @return the gradle versions
     */
    protected List<String> getGradleVersionsToTest() {
        return Stream.of("7.2").collect(Collectors.toList());
    }

    /**
     * Gets a {@code GradleRunner} configured with default settings.
     *
     * @param gradleVersion the gradle version
     * @param args arguments to the gradle command
     * @return a runner
     */
    EnhancedGradleRunner runner(String gradleVersion, String... args) {
        List<String> argList = Stream.concat(Arrays.stream(args), getDefaultArgs().stream())
            .collect(Collectors.toList());

        return EnhancedGradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(testProjectDir)
            .withArguments(argList)
            .withPluginClasspath()
            .forwardOutput();
    }

    /**
     * Default arguments to use with the {@code GradleRunner}.<p>
     * @return the list of arguments.
     */
    protected List<String> getDefaultArgs() {
        return DEFAULT_ARGS;
    }

    private static String toKebabLowerCase(String s) {
        String kebab = s.replaceAll("([a-z])([A-Z]+)", "$1-$2");
        return kebab.toLowerCase();
    }
}
