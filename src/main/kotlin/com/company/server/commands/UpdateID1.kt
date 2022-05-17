package com.company.server.commands

import com.company.Message
import com.company.collection.LabWorkClient
import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import kotlin.properties.Delegates

class UpdateID1(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "update1"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Update element to collection from console by id"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is Message && any.any is LabWorkClient) {
            try {
                var id by Delegates.notNull<Int>()
                try {
                    id = any.string.toInt()
                } catch (e: NumberFormatException) {
                    throw IllegalArgumentException("It's not number!")
                }
                val date = labWorkCollections.findLabWork(id).creationDate
                labWorkCollections.removeLabWork(id)
                labWorkCollections.addLabWork(labWorkCreator.inputLabWorkFromClient(any.any, id, date))
                return "Update successful"
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }

}