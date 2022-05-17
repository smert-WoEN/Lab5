package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
@Deprecated("")
class AddIfMax(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {



    override fun getLabel(): String {
        return "addIfMax"
    }

    override fun getDescription(): String {
        return "Add new element to collection from console if max more then maxElement"
    }

    override fun execute(argument: String): String {
        val labWork = (labWorkCreator.inputNewLabWorkFromConsole())
        if (labWorkCollections.findMaxPointValue() < labWork.maximalPoint) {
            labWorkCollections.addLabWork(labWork)
        } else {
            throw IllegalArgumentException("new labWork less then maxValue in collection.")
        }
        return "Add successful"
    }
}