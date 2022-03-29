package com.company.commands

import com.company.collection.LabWorkCollections

class Clear(private val labWorkCollections: LabWorkCollections): Command {

    override fun getLabel(): String {
        return "clear"
    }

    override fun getDescription(): String {
        return "Clear the collection"
    }

    override fun execute(argument: String): String {

        TODO("need make exit")
        //labWorkCollections.clear()//labWorkCollections.clear()
        return "Collection cleared"
    }
}