package org.mostlylikeable.gradle.test;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.function.Consumer;

public final class IoUtils {

    @SneakyThrows
    public static <T extends Closeable> void use(T closeable, Consumer<T> action) {
        try (closeable) {
            action.accept(closeable);
        }
    }

    @SneakyThrows
    public static void appendAndClose(File f, String s) {
        use(writer(f), it -> append(it, s));
    }

    @SneakyThrows
    public static <T extends Writer> T append(T writer, String s) {
        writer.append(s);
        return writer;
    }

    @SneakyThrows
    public static Writer writer(File f) {
        return new BufferedWriter(new FileWriter(f, true));
    }
}
