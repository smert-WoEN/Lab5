package com.company.commands

import com.company.collection.LabWorkCollections
import kotlin.properties.Delegates
@Deprecated("")
class RemoveID (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "remove"
    }

    override fun getDescription(): String {
        return "remove element from collection by id"
    }

    override fun execute(argument: String): String {
        var id by Delegates.notNull<Int>()
        try {
            id = argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        labWorkCollections.removeLabWork(id)
        return "Remove successful."
    }
}