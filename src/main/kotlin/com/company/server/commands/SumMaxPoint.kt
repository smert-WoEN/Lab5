package com.company.server.commands

import com.company.collection.LabWorkCollections

class SumMaxPoint(private val labWorkCollections: LabWorkCollections): Command {
    override fun getLabel(): String {
        return "sumMaxPoint"
    }

    override fun getDescription(): String {
        return "sum maxPoint element from collection"
    }

    override fun execute(any: Any): String {
        return if (any is String) {
            try {
                "sum of maxPint: " + labWorkCollections.sumMaxPoint()
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}