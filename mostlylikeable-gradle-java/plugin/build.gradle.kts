plugins {
    kotlin("jvm")
    groovy
    `java-gradle-plugin`
}

group = "mostlylikeable.java"

val integrationTest: SourceSet by sourceSets.creating

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}")

    testImplementation(platform("org.spockframework:spock-bom:2.0-groovy-3.0"))
    testImplementation("org.spockframework:spock-core")
    testImplementation("org.spockframework:spock-junit4")
    testImplementation("junit:junit:4.13.1")

    "integrationTestImplementation"(project)
    "integrationTestImplementation"(platform("org.spockframework:spock-bom:2.0-groovy-3.0"))
    "integrationTestImplementation"("org.spockframework:spock-core")
    "integrationTestImplementation"("org.spockframework:spock-junit4")
    "integrationTestImplementation"("junit:junit:4.13.1")
}

val integrationTestTask = tasks.register<Test>("integrationTest") {
    description = "Runs the integration tests."
    group = "verification"
    testClassesDirs = integrationTest.output.classesDirs
    classpath = integrationTest.runtimeClasspath
    mustRunAfter(tasks.test)
}

tasks.check {
    dependsOn(integrationTestTask)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

gradlePlugin {
    testSourceSets(integrationTest)
    plugins {
        create("MostlyLikeableJavaPlugin") {
            id = "mostlylikeable-java"
            group = project.group
            implementationClass = "mostlylikeable.java.MostlyLikeableJavaPlugin"
        }
    }
}
