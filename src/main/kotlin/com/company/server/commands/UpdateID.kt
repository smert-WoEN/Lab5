package com.company.server.commands

import com.company.collection.LabWorkComparator

class UpdateID(private val labWorkComparator: LabWorkComparator): Command {
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
        return if (any is String) {
            try {
                val args = any.split(" ", limit = 2)
                labWorkComparator.findLabWork(args[0].toInt(), args[1])
                "yes"
            } catch (e: IllegalArgumentException) {
                e.message!!
            } catch (e: RuntimeException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}