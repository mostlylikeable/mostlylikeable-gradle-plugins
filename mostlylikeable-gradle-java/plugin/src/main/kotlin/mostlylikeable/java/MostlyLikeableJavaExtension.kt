package mostlylikeable.java

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import javax.inject.Inject
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions

const val DEFAULT_JAVA_VERSION: String = "1.8"

/**
 * _NOTE: setting `sourceCompatibility` or `targetCompatibility` via `java` plugin will override the settings for
 * this plugin._
 */
abstract class MostlyLikeableJavaExtension @Inject constructor(private val project: Project) {

    abstract val javaCompatibility: Property<JavaVersion>

    /**
     * Returns {@code true} if compatibility is not set in java-plugin(s)
     *
     * _Assumption: java extension is `DefaultJavaPluginExtension`, which has "raw" values that are for configured
     * settings. `sourceCompatibility` will default and do other checks.
     */
    fun useJavaPluginCompatibility(): Boolean {
        return javaExtSourceCompatibility() != null || javaExtTargetCompatibility() != null
    }

    fun javaCompatibilityOrDefault(): JavaVersion {
        return javaCompatibility.getOrElse(JavaVersion.toVersion(DEFAULT_JAVA_VERSION))
    }

    private fun javaExt(): JavaPluginExtension {
        return project.extensions.getByType(JavaPluginExtension::class.java)
    }

    /**
     * Gets "raw" source compatibility from java extension.
     */
    private fun javaExtSourceCompatibility(): JavaVersion? {
        val javaExt = javaExt()
        return findGetter<JavaVersion>(javaExt, "getRawSourceCompatibility")?.call(javaExt)
    }

    /**
     * Gets "raw" target compatibility from java extension.
     */
    private fun javaExtTargetCompatibility(): JavaVersion? {
        val javaExt = javaExt()
        return findGetter<JavaVersion>(javaExt, "getRawTargetCompatibility")?.call(javaExt)
    }

    private fun <R> findGetter(type: Any, name: String): KFunction<R?>? {
        @Suppress("UNCHECKED_CAST")
        return type::class.functions.find { it.name == name } as KFunction<R?>?
    }
}
