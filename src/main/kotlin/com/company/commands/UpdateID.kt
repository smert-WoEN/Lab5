package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import kotlin.properties.Delegates

class UpdateID(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "update"
    }

    override fun getDescription(): String {
        return "Update element to collection from console by id"
    }

    override fun execute(argument: String): String {
        var id by Delegates.notNull<Int>()
        try {
            id = argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        val date = labWorkCollections.findLabWork(id).creationDate
        labWorkCollections.removeLabWork(id)
        labWorkCollections.addLabWork(labWorkCreator.inputLabWorkFromConsole(id, date))
        return "Update successful"
    }
}