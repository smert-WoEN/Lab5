package com.company.server.commands

import com.company.collection.LabWorkCollections
import com.company.files.LabWorkToString

class FilterMaxPoint(private val labWorkCollections: LabWorkCollections): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "filterMaxPoint"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "filter by max Point"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is Double) {
            try {
                val group = labWorkCollections.findLabWorkCompareMaxPoint(any)
                var string = ""
                group.forEach { value -> string += "${LabWorkToString(value).labWorkToString()} \n" }
                return string
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad value, try again"
        }
    }
}