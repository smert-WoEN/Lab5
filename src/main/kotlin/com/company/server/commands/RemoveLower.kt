package com.company.server.commands

import com.company.collection.LabWorkComparator

class RemoveLower(private val labWorkComparator: LabWorkComparator): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "removeLower"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "removeLover element from collection by minValue"
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
                val args = any.split(" ", limit = 2)
                labWorkComparator.removeLessMinPoint(args[0].toInt(), args[1])
                "Remove successful."
            } catch (e: IllegalArgumentException) {
                e.message!!
            } catch (e: RuntimeException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }    }
}