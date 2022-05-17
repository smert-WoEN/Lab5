package com.company.server.commands

import com.company.collection.LabWorkCollections

class GroupMinPoint(private val labWorkCollections: LabWorkCollections): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "groupMinPoint"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "group counting by min point"
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
                val group = labWorkCollections.groupMinPoint()
                var string = ""
                group.forEach { (key, value) ->
                    string += "minPoint: $key: ${value.size} \n"
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