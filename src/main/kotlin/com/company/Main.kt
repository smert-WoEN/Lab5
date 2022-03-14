package com.company

import java.text.DateFormat
import java.text.SimpleDateFormat


fun main() {
    val date = java.util.Date()
    println(date)
    println(date.time)
    println(java.util.Date(date.time))
    val df: DateFormat = SimpleDateFormat("EEE-MMM-dd-HH:mm:ss-zzz-yyyy")
    val result = df.parse("Mon Mar 14 20:32:59 MSK 2022")
    println(result)
    println(DateFormat.getDateInstance(DateFormat.MEDIUM).parse("Mon Mar 14 20:32:59 MSK 2022"))
    /*var labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
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
        val fileReader = FileReader(systemPathToRoad)
        val s = fileReader.readLines();
        println(s)
        println(s.size)
    } catch (e: RuntimeException) {
        System.err.println(e)
    }*/
}

