plugins {
    kotlin("jvm")
    groovy
    `java-gradle-plugin`
}

group = "mostlylikeable.java"

val functionalTest: SourceSet by sourceSets.creating

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}")

    testImplementation(platform("org.spockframework:spock-bom:2.0-groovy-3.0"))
    testImplementation("org.spockframework:spock-core")
    testImplementation("org.spockframework:spock-junit4")
    testImplementation("junit:junit:4.13.1")

    "functionalTestImplementation"(project)
    "functionalTestImplementation"(platform("org.spockframework:spock-bom:2.0-groovy-3.0"))
    "functionalTestImplementation"("org.spockframework:spock-core")
    "functionalTestImplementation"("org.spockframework:spock-junit4")
    "functionalTestImplementation"("junit:junit:4.13.1")
}

val functionalTestTask = tasks.register<Test>("functionalTest") {
    description = "Runs the functional tests."
    group = "verification"
    testClassesDirs = functionalTest.output.classesDirs
    classpath = functionalTest.runtimeClasspath
    mustRunAfter(tasks.test)
}

tasks.check {
    dependsOn(functionalTestTask)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

gradlePlugin {
    testSourceSets(functionalTest)
    plugins {
        create("MostlyLikeableJavaPlugin") {
            id = "mostlylikeable-java"
            group = project.group
            implementationClass = "mostlylikeable.java.MostlyLikeableJavaPlugin"
        }
    }
}
