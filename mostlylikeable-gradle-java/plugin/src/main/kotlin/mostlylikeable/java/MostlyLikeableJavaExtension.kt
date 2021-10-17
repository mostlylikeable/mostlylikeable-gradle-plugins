package mostlylikeable.java

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class MostlyLikeableJavaExtension @Inject constructor(private val project: Project) {

    abstract val javaCompatibility: Property<JavaVersion>

    fun getCompatibilityOrDefault(): JavaVersion {
        return javaCompatibility.getOrElse(
            project.extensions.getByType(JavaPluginExtension::class.java).sourceCompatibility
        )
    }
}
