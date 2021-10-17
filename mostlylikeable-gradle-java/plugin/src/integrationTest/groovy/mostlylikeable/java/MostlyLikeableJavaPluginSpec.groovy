package mostlylikeable.java

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class MostlyLikeableJavaPluginSpec extends Specification {

    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        buildFile << '''
            plugins {
                id "mostlylikeable-java"
            }

            tasks.register("printJavaVersion") {
                doLast {
                    println "java:"
                    println "  source-version: ${java.sourceCompatibility}"
                    println "  target-version: ${java.targetCompatibility}"
                }
            }
        '''
    }

    def "java plugin applied"() {
        when:
        BuildResult result = createRunner()
            .withArguments("tasks", "--all")
            .build()

        then:
        result.output.contains("compileJava - Compiles main Java source.")
        result.output.contains("jar - Assembles a jar archive containing the main classes.")
    }

    def "compatibility is defaulted"() {
        when:
        BuildResult result = createRunner()
            .withArguments("printJavaVersion")
            .build()

        then:
        result.task(":printJavaVersion").outcome == SUCCESS
        result.output.contains("source-version: 1.8")
        result.output.contains("target-version: 1.8")
    }

    def "compatibility can be set via plugin"() {
        setup:
        buildFile << '''
            mostlylikeableJava {
                javaCompatibility = org.gradle.api.JavaVersion.VERSION_11
            }
        '''

        when:
        BuildResult result = createRunner()
            .withArguments("printJavaVersion")
            .build()

        then:
        result.task(":printJavaVersion").outcome == SUCCESS
        result.output.contains("source-version: 11")
        result.output.contains("target-version: 11")
    }

    def "does not override java plugin source setting"() {
        setup:
        buildFile << '''
            java {
                sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_9
            }
        '''

        when:
        BuildResult result = createRunner()
            .withArguments("printJavaVersion")
            .build()

        then:
        result.task(":printJavaVersion").outcome == SUCCESS
        result.output.contains("Compatibility is set in java plugin. Using that instead.")
        result.output.contains("source-version: 1.9")
        result.output.contains("target-version: 1.9")
    }

    def "does not override java plugin target setting"() {
        setup:
        buildFile << '''
            java {
                targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_9
            }
        '''

        when:
        BuildResult result = createRunner()
            .withArguments("printJavaVersion")
            .build()

        then:
        result.task(":printJavaVersion").outcome == SUCCESS
        result.output.contains("Compatibility is set in java plugin. Using that instead.")
        result.output.contains("source-version: 11")
        result.output.contains("target-version: 1.9")
    }

    private GradleRunner createRunner() {
        GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withPluginClasspath()
            .forwardOutput()
    }
}
