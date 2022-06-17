package com.company.server.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator

class SumMaxPoint(private val labWorkComparator: LabWorkComparator): Command {
    override fun getLabel(): String {
        return "sumMaxPoint"
    }

    override fun getDescription(): String {
        return "sum maxPoint element from collection"
    }

    override fun execute(any: Any): String {
        return if (any is String) {
            try {
                "sum of maxPint: " + labWorkComparator.sumMaxPoint()
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }
    }
}