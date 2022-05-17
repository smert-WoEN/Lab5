package com.company.server.commands

import com.company.collection.LabWorkClient
import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator

class Add(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "add"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Add new element to collection from client"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is LabWorkClient) {
            try {
                labWorkCollections.addLabWork(labWorkCreator.inputNewLabWorkFromClient(any))
                "add successful"
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }

    }
}