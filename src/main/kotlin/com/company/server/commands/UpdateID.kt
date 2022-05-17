package com.company.server.commands

import com.company.collection.LabWorkCollections

class UpdateID(private val labWorkCollections: LabWorkCollections): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "update"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Update element to collection from client by id"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is Int) {
            try {
                labWorkCollections.findLabWork(any)
                "yes"
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}