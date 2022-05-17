package com.company.commands

import com.company.collection.LabWorkCollections
@Deprecated("")
class Info (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "info"
    }

    override fun getDescription(): String {
        return "give information about collection"
    }

    override fun execute(argument: String): String {
        return "LabWork collection, initialization date: " + labWorkCollections.dateInInitialize + ", number of elements: " + labWorkCollections.getCountElements() + "."
    }
}