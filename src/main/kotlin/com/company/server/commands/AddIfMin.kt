package com.company.server.commands

import com.company.collection.LabWorkClient
import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator
import com.company.collection.LabWorkCreator

class AddIfMin(private val labWorkComparator: LabWorkComparator): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "addIfMin"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Add new element to collection from client if min less then maxElement"
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
                if (labWorkComparator.findMinPointValue() > any.minimalPoint) {
                    labWorkComparator.addLabWork(any)
                    "add successful"
                } else {
                    "max point less then max in collection"
                }
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}