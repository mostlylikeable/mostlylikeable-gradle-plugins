tasks.wrapper {
    gradleVersion = "7.2"
    distributionType = Wrapper.DistributionType.BIN
}

// NOTE: 'gradle clean build' will not work because task ordering in the task graph gets weird. Some "clean" tasks end
//   up having dependencies on other tasks, so we get a weird partially built state and "build" will fail.
//
// need to do 'gradle clean && gradle build' instead.
// the issue is discussed here: https://github.com/gradle/gradle/issues/2488
listOf("clean", "check", "build").forEach { name ->
    tasks.register(name) {
        dependsOn(gradle.includedBuilds.map { it.task(":$name") })
    }
}
