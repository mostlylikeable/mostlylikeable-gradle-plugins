package org.mostlylikeable.gradle.info.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

abstract class ProjectInfoTask : DefaultTask() {

    @get:Input
    var title: String = ""

    @get:Internal
    protected val sectionsByTitle = mutableMapOf<String, Section>()

    @get:Internal
    protected val errors = Section("Errors")

    @get:Internal
    protected val print = InfoPrinter()

    init {
        group = "project info"
    }

    @TaskAction
    open fun printInfo() {
        populateInfo()
        print.title(title)

        sectionsByTitle.values.forEach { printSection(it) }
        if (errors.isNotEmpty()) { printSection(errors) }
    }

    protected abstract fun populateInfo()

    private fun printSection(section: Section) {
        print.sectionTitle(section.title)
        section.lines.forEach { line ->
            print.text(line)
        }
    }

    fun addSection(section: Section): Section {
        sectionsByTitle[section.title] = section
        return section
    }

    fun addSections(vararg sections: Section ) {
        sections.forEach { addSection(it) }
    }

    fun addSections(sections: Iterable<Section>) {
        sections.forEach { addSection(it) }
    }

    fun section(title: String, action: (Section.() -> Unit)? = null): Section {
        val sec = (if (!sectionsByTitle.containsKey(title)) addSection(Section(title)) else sectionsByTitle[title])!!
        if (action != null) { action(sec) }
        return sec
    }

    fun error(err: String) {
        errors.lines += err
    }

    class Section constructor(val title: String, val sorted: Boolean = true) {
        val lines = if (sorted) mutableSetOf<String>().toSortedSet() else mutableSetOf<String>()

        fun line(s: String) {
            lines += s
        }

        fun isNotEmpty(): Boolean {
            return lines.isNotEmpty()
        }
    }
}
