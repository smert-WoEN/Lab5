package com.company.server.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator
import com.company.files.LabWorkToString

class Show(private val labWorkComparator: LabWorkComparator): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "show"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "show elements"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is String) {
            try {
                val group = labWorkComparator.getCollections()
                var string = ""
                group.forEach { value ->
                    string += "${LabWorkToString(value).labWorkToString()} \n"
                }
                return string
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }

}