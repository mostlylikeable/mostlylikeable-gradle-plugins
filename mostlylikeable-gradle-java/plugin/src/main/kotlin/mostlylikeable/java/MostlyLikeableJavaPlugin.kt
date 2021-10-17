package mostlylikeable.java

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension

const val EXT_NAME = "mostlylikeableJava"

open class MostlyLikeableJavaPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply(JavaPlugin::class.java)

            val ext = extensions.create(EXT_NAME, MostlyLikeableJavaExtension::class.java)

            configureJava(this, ext)
        }
    }

    protected open fun configureJava(project: Project, ext: MostlyLikeableJavaExtension) {
        with(project) {
            afterEvaluate {
                // needs to happen after-eval so java ext properties are set when we check compatibility
                if (ext.useJavaPluginCompatibility()) {
                    logger.warn("Compatibility is set in java plugin. Using that instead.")
                } else {
                    extensions.configure(JavaPluginExtension::class.java) {
                        it.sourceCompatibility = ext.javaCompatibilityOrDefault()
                        it.targetCompatibility = it.sourceCompatibility
                    }
                }
            }
        }
    }
}
