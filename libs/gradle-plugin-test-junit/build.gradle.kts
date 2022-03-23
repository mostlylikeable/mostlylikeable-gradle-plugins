plugins {
    id("build.java-library")
}

dependencies {
    val lombokLib = "org.projectlombok:lombok:1.18.22"
    annotationProcessor(lombokLib)
    compileOnly(lombokLib)

    api("org.junit.jupiter:junit-jupiter:5.7.1")
    api("$group:gradle-testkit-ext:$version")

    testAnnotationProcessor(lombokLib)
    testCompileOnly(lombokLib)
}
