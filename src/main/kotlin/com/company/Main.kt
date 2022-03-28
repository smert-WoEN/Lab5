package com.company

import com.company.collection.Coordinates
import com.company.collection.Discipline
import com.company.collection.LabWork
import com.company.collection.LabWorkCollections
import com.company.files.*
import com.company.superclasses.Difficulty
import java.io.FileReader
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    var labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
    Difficulty.EASY, Discipline("asd", null, 10, 10L, 50)
    )
    val labWorkCollections = LabWorkCollections()
    labWorkCollections.labWorkCollections.add(labWork)
    labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
        Difficulty.EASY, Discipline("asd", null, 10, 10L, 510))
    labWorkCollections.labWorkCollections.add(labWork)
    println(LabWorkToString(labWork).labWorkToString(csv = true))
    println(labWorkCollections.labWorkCollections)
    lateinit var listFromFile: ListFromFile
    var flag = false
    try {
        val systemPathToRoad: String = SystemPathToRoad("JavaOut").path!!
        val listToFile = ListToFile(systemPathToRoad, HashSetToList(labWorkCollections.labWorkCollections).list, 1)
        listToFile.fileWrite()
        System.`in`.read()
        listFromFile = ListFromFile(systemPathToRoad)
        flag = true
        listFromFile.fileRead()
    } catch (e: RuntimeException) {
        System.err.println(e.message)
    }
    if (flag) {
        println(listFromFile.list)
    }

}

