package org.mostlylikeable.gradle.test;

public final class Strings {

    public static String multiline(String... lines) {
        return String.join("\n", lines);
    }
}
