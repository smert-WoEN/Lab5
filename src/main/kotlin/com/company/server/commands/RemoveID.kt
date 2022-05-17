package com.company.server.commands

import com.company.collection.LabWorkCollections

class RemoveID(private val labWorkCollections: LabWorkCollections): Command {
    override fun getLabel(): String {
        return "remove"
    }

    override fun getDescription(): String {
        return "remove element from collection by id"
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
                labWorkCollections.removeLabWork(any)
                "remove successful"
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }    }
}