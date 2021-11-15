package org.mostlylikeable.gradle.testkit;

import org.gradle.testkit.runner.internal.DefaultGradleRunner;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * Typed wrapper around {@code DefaultGradleRunner} that allows subtypes not to have to worry about casting in the
 * builder-like methods.
 *
 * @param <T> the type of the runner
 */
public class TypedDefaultGradleRunner<T extends DefaultGradleRunner> extends DefaultGradleRunner {

    @Override @SuppressWarnings("unchecked")
    public T withGradleVersion(String versionNumber) {
        return (T) super.withGradleVersion(versionNumber);
    }

    @Override @SuppressWarnings("unchecked")
    public T withGradleInstallation(File installation) {
        return (T) super.withGradleInstallation(installation);
    }

    @Override @SuppressWarnings("unchecked")
    public T withTestKitDir(File testKitDir) {
        return (T) super.withTestKitDir(testKitDir);
    }

    @Override @SuppressWarnings("unchecked")
    public T withJvmArguments(List<String> jvmArguments) {
        return (T) super.withJvmArguments(jvmArguments);
    }

    @Override @SuppressWarnings("unchecked")
    public T withJvmArguments(String... jvmArguments) {
        return (T) super.withJvmArguments(jvmArguments);
    }

    @Override @SuppressWarnings("unchecked")
    public T withProjectDir(File projectDir) {
        return (T) super.withProjectDir(projectDir);
    }

    @Override @SuppressWarnings("unchecked")
    public T withArguments(List<String> arguments) {
        return (T) super.withArguments(arguments);
    }

    @Override @SuppressWarnings("unchecked")
    public T withArguments(String... arguments) {
        return (T) super.withArguments(arguments);
    }

    @Override @SuppressWarnings("unchecked")
    public T withPluginClasspath() {
        return (T) super.withPluginClasspath();
    }

    @Override @SuppressWarnings("unchecked")
    public T withPluginClasspath(Iterable<? extends File> classpath) {
        return (T) super.withPluginClasspath(classpath);
    }

    @Override @SuppressWarnings("unchecked")
    public T withDebug(boolean flag) {
        return (T) super.withDebug(flag);
    }

    @Override @SuppressWarnings("unchecked")
    public T withEnvironment(Map<String, String> environment) {
        return (T) super.withEnvironment(environment);
    }

    @Override @SuppressWarnings("unchecked")
    public T forwardStdOutput(Writer writer) {
        return (T) super.forwardStdOutput(writer);
    }

    @Override @SuppressWarnings("unchecked")
    public T forwardStdError(Writer writer) {
        return (T) super.forwardStdError(writer);
    }

    @Override @SuppressWarnings("unchecked")
    public T forwardOutput() {
        return (T) super.forwardOutput();
    }

    @Override @SuppressWarnings("unchecked")
    public T withStandardInput(InputStream standardInput) {
        return (T) super.withStandardInput(standardInput);
    }
}
