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
        buildFile << """
            plugins {
                id "mostlylikeable-java"
            }
        """
    }

    def "java plugin is added"() {
        when:
        BuildResult result = createRunner()
            .withArguments("tasks", "--all")
            .build()

        then:
        result.output.contains("compileJava - Compiles main Java source.")
        result.output.contains("jar - Assembles a jar archive containing the main classes.")
    }

    def "java versions defaulted"() {
        setup:
        buildFile << '''
            tasks.register("printJavaVersion") {
                doLast {
                    println "java:"
                    println "  source-version: ${java.sourceCompatibility}"
                    println "  target-version: ${java.targetCompatibility}"
                }
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

    def "java versions can be overridden via plugin"() {
        setup:
        buildFile << '''
            import org.gradle.api.JavaVersion
            
            mostlylikeableJava {
                javaCompatibility = JavaVersion.VERSION_11
            }

            tasks.register("printJavaVersion") {
                doLast {
                    println "java:"
                    println "  source-version: ${java.sourceCompatibility}"
                    println "  target-version: ${java.targetCompatibility}"
                }
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

    def "java versions can be overridden via java plugin"() {
        setup:
        buildFile << '''
            import org.gradle.api.JavaVersion
            
            java {
                sourceCompatibility = JavaVersion.VERSION_1_9
                targetCompatibility = JavaVersion.VERSION_1_9
            }

            tasks.register("printJavaVersion") {
                doLast {
                    println "java:"
                    println "  source-version: ${java.sourceCompatibility}"
                    println "  target-version: ${java.targetCompatibility}"
                }
            }
        '''

        when:
        BuildResult result = createRunner()
            .withArguments("printJavaVersion")
            .build()

        then:
        result.task(":printJavaVersion").outcome == SUCCESS
        result.output.contains("source-version: 1.9")
        result.output.contains("target-version: 1.9")
    }

    private GradleRunner createRunner() {
        GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withPluginClasspath()
            .forwardOutput()
    }
}
