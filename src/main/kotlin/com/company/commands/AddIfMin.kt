package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
@Deprecated("")
class AddIfMin(private val labWorkCreator: LabWorkCreator, private val labWorkCollections: LabWorkCollections): Command {

    override fun getLabel(): String {
        return "addIfMin"
    }

    override fun getDescription(): String {
        return "Add new element to collection from console if min less then minElement"
    }

    override fun execute(argument: String): String {
        val labWork = (labWorkCreator.inputNewLabWorkFromConsole())
        if (labWorkCollections.findMinPointValue() > labWork.minimalPoint) {
            labWorkCollections.addLabWork(labWork)
        } else {
            throw IllegalArgumentException("new labWork more then minValue in collection.")
        }
        return "Add successful"
    }
}