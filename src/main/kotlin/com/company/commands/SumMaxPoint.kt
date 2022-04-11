package com.company.commands

import com.company.collection.LabWorkCollections
import kotlin.properties.Delegates

class SumMaxPoint(private val labWorkCollections: LabWorkCollections): Command {


    override fun getLabel(): String {
        return "sumMaxPoint"
    }

    override fun getDescription(): String {
        return "sum maxPoint element from collection"
    }

    override fun execute(argument: String): String {
        return "sum of maxPint: " + labWorkCollections.sumMaxPoint()
    }
}