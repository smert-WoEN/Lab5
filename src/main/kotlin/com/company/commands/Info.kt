package com.company.commands

import com.company.collection.LabWorkCollections

class Info (private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "info"
    }

    override fun getDescription(): String {
        return "dive information about collection"
    }

    override fun execute(argument: String): String {
        return "Dragon collection, initialization date: " + labWorkCollections.dateInInitialize + ", number of elements: " + labWorkCollections.getCountElements() + "."
    }
}