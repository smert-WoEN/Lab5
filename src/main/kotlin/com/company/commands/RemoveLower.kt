package com.company.commands

import com.company.collection.LabWorkCollections
import kotlin.properties.Delegates
@Deprecated("")
class RemoveLower (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "removeLover"
    }

    override fun getDescription(): String {
        return "removeLover element from collection by minValue"
    }

    override fun execute(argument: String): String {
        var minPoint by Delegates.notNull<Int>()
        try {
            minPoint = argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        labWorkCollections.removeLessMinPoint(minPoint)
        return "Remove successful."
    }
}