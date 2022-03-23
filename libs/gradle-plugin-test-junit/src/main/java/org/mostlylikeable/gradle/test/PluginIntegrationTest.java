package org.mostlylikeable.gradle.test;

import org.junit.jupiter.api.io.TempDir;

import java.io.File;

public abstract class PluginIntegrationTest {

    @TempDir File testProjectDir;

    abstract String getPluginId();
}
