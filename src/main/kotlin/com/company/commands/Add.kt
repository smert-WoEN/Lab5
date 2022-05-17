package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator

@Deprecated("")
class Add(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {



    override fun getLabel(): String {
        return "add"
    }

    override fun getDescription(): String {
        return "Add new element to collection from console"
    }

    override fun execute(argument: String): String {
        labWorkCollections.addLabWork(labWorkCreator.inputNewLabWorkFromConsole())
        return "Add successful"
    }
}