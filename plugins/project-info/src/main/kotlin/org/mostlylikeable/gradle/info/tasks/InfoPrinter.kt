package org.mostlylikeable.gradle.info.tasks

class InfoPrinter {

    fun newline() {
        println()
    }

    fun title(title: String) {
        newline()
        text("-".repeat(title.length))
        text(title)
        text("-".repeat(title.length))
    }

    fun sectionTitle(title: String) {
        newline()
        text(title)
        text("-".repeat(title.length))
    }

    fun text(s: String) {
        println(s)
    }
}
