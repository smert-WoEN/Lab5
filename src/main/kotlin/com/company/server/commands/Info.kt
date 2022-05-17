package com.company.server.commands

import com.company.collection.LabWorkCollections

class Info(private val labWorkCollections: LabWorkCollections):Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "info"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "give information about collection"
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
                "LabWork collection, initialization date: " + labWorkCollections.dateInInitialize + ", number of elements: " + labWorkCollections.getCountElements() + "."
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }

}