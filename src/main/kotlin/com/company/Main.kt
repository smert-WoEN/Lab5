package com.company

import com.company.collection.*
import com.company.files.HashSetToList
import com.company.files.LabWorkToString
import com.company.files.ListToFile
import com.company.files.SystemPathToRoad
import com.company.superclasses.Difficulty
import java.util.*


fun main() {
    var labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
    Difficulty.EASY, Discipline("asd", null, 10, 10L, 50))
    val labWorkCollections = LabWorkCollections()
    labWorkCollections.labWorkCollections.add(labWork)
    labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
        Difficulty.EASY, Discipline("asd", null, 10, 10L, 510))
    labWorkCollections.labWorkCollections.add(labWork)
    println(LabWorkToString(labWork).labWorkToString(csv = true))
    println(labWorkCollections.labWorkCollections)
    try {
        val systemPathToRoad: String = SystemPathToRoad("JavaOut").path!!
        val listToFile = ListToFile(systemPathToRoad, HashSetToList(labWorkCollections.labWorkCollections).list)
        listToFile.fileWrite()
    } catch (e: RuntimeException) {
        System.err.println(e)
    }
}

