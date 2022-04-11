package com.company.commands

import com.company.collection.LabWorkCollections
import kotlin.properties.Delegates

class GroupMinPoint (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "groupMinPoint"
    }

    override fun getDescription(): String {
        return "group counting by min point"
    }

    override fun execute(argument: String): String {
        val group = labWorkCollections.groupMinPoint()
        var string = ""
        group.forEach { (key, value) ->
            string += "minPoint: $key: ${value.size} \n"
        }
        return string
    }
}