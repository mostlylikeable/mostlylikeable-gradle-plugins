package mostlylikeable.java

import org.gradle.api.JavaVersion
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
            extensions.configure(JavaPluginExtension::class.java) {
                it.sourceCompatibility = ext.javaVersion
                it.targetCompatibility = it.sourceCompatibility
            }
        }
    }
}
