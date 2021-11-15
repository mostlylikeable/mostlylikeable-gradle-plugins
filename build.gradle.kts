tasks.wrapper {
    gradleVersion = "7.2"
    distributionType = Wrapper.DistributionType.BIN
}

listOf("clean", "check", "build").forEach { name ->
    tasks.register(name) {
        dependsOn(gradle.includedBuilds.map { it.task(":$name") })
    }
}
