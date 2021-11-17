plugins {
    id("build.groovy-library")
}

dependencies {
    api("org.spockframework:spock-core:2.0-groovy-3.0") {
        exclude(group = "org.codehaus.groovy")
    }
    api("mostlylikeable.gradle:gradle-testkit-ext:$version")
}
