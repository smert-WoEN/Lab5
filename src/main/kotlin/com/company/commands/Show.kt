package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.files.LabWorkToString
@Deprecated("")
class Show (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "show"
    }

    override fun getDescription(): String {
        return "show elements"
    }

    override fun execute(argument: String): String {
        val group = labWorkCollections.getCollections()
        var string = ""
        group.forEach { value ->
            string += "${LabWorkToString(value).labWorkToString()} \n"
        }
        return string
    }
}