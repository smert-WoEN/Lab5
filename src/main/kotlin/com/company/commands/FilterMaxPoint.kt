package com.company.commands

import com.company.collection.LabWork
import com.company.collection.LabWorkCollections
import com.company.files.LabWorkToString
import kotlin.math.max
import kotlin.properties.Delegates

class FilterMaxPoint (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "filterMaxPoint"
    }

    override fun getDescription(): String {
        return "filter by max Point"
    }

    override fun execute(argument: String): String {
        var maxPoint by Delegates.notNull<Double>()
        try {
            maxPoint = argument.toDouble()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        val group = labWorkCollections.findLabWorkCompareMaxPoint(maxPoint)
        var string = ""
        group.forEach { value ->
            string += "${LabWorkToString(value).labWorkToString()} \n"
        }
        return string
    }
}