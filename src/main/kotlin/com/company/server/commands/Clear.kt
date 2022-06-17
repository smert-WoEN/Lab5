package com.company.server.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator

class Clear(private val labWorkComparator: LabWorkComparator): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "clear"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Clear the collection"
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
                labWorkComparator.clearElements(any)
                "clear successful"
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}