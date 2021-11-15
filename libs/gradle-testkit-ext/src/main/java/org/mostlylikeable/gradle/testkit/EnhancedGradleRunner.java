package org.mostlylikeable.gradle.testkit;

public class EnhancedGradleRunner extends TypedDefaultGradleRunner<EnhancedGradleRunner> {

    public static EnhancedGradleRunner create() {
        return new EnhancedGradleRunner();
    }

    @Override
    public EnhancedBuildResult build() {
        return new EnhancedBuildResult(super.build());
    }

    @Override
    public EnhancedBuildResult buildAndFail() {
        return new EnhancedBuildResult(super.buildAndFail());
    }
}
