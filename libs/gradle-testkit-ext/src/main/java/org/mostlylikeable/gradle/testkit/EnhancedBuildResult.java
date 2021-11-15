package org.mostlylikeable.gradle.testkit;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.TaskOutcome;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class EnhancedBuildResult implements BuildResult {

    private final BuildResult delegate;

    EnhancedBuildResult(BuildResult delegate) {
        this.delegate = delegate;
    }

    public EnhancedBuildResult assertOutput(Collection<String> expected) {
        return assertOutput(expected.stream());
    }

    public EnhancedBuildResult assertOutput(String... expected) {
        return assertOutput(Arrays.stream(expected));
    }

    public EnhancedBuildResult assertOutput(Stream<String> expected) {
        String actual = getOutput();
        expected.forEach(s -> {
            assert actual.contains(s): String.format("%s expected but NOT found in output.\noutput:\n%s", s, actual);
        });
        return this;
    }

    public EnhancedBuildResult assertOutputNot(Collection<String> notExpected) {
        return assertOutputNot(notExpected.stream());
    }

    public EnhancedBuildResult assertOutputNot(String... notExpected) {
        return assertOutputNot(Arrays.stream(notExpected));
    }

    public EnhancedBuildResult assertOutputNot(Stream<String> notExpected) {
        String actual = getOutput();
        notExpected.forEach(s -> {
            assert actual.contains(s): String.format("%s NOT expected but found in output.\noutput:\n%s", s, actual);
        });
        return this;
    }

    @Override
    public String getOutput() {
        return delegate.getOutput();
    }

    @Override
    public List<BuildTask> getTasks() {
        return delegate.getTasks();
    }

    @Override
    public List<BuildTask> tasks(TaskOutcome taskOutcome) {
        return delegate.tasks(taskOutcome);
    }

    @Override
    public List<String> taskPaths(TaskOutcome taskOutcome) {
        return delegate.taskPaths(taskOutcome);
    }

    @Nullable
    @Override
    public BuildTask task(String s) {
        return delegate.task(s);
    }
}
