package com.company

import com.company.collection.*
import com.company.files.*
import com.company.ui.UserRunnable
import java.util.*


fun main() {
    val printStream = System.out
    val errorStream = System.err
    val scanner = Scanner(System.`in`)
    val pathName = "JavaOut"
    try {
        val pathToRoad = SystemPathToRoad(pathName).path
        val labWorkCollections = LabWorkCollections()
        lateinit var labWorkCreator: LabWorkCreator
        try {
            val listFromFile = ListFromFile(pathToRoad)
            try {
                listFromFile.fileRead()
            } catch (e: NumberFormatException) {
                errorStream.println(e.message)
            }
            labWorkCreator = LabWorkCreator(printStream, errorStream, scanner, listFromFile.id)
            labWorkCollections.setFromList(ListToHashSet(listFromFile.list).hashSet)
        } catch (e: IllegalArgumentException) {
            errorStream.println(e.message)
            labWorkCreator = LabWorkCreator(printStream, errorStream, scanner)
        }

        val userRunnable =
            UserRunnable(labWorkCollections, labWorkCreator,pathToRoad, printStream, errorStream, scanner)
        userRunnable.run()
    } catch (e: IllegalArgumentException) {
        errorStream.println(e.message + " program can't load.")
    }



//    var labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
//        Difficulty.EASY, Discipline("asd", null, 10, 10L, 50))
//    val labWorkCollections = LabWorkCollections()
//    labWorkCollections.addLabWork(labWork)
//    labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 110, 1000.0,
//        Difficulty.EASY, Discipline("asd", null, 10, 10L, 510))
//    labWorkCollections.addLabWork(labWork)
//    labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 1040, 100.0,
//        Difficulty.EASY, Discipline("asd", null, 10, 10L, 5101))
//    labWorkCollections.addLabWork(labWork)
//    labWork = LabWork(1, "sd", Coordinates(200, 200.0), Date(), 100, 100.0,
//        Difficulty.EASY, Discipline("asd", null, 10, 10L, 5102))
//    labWorkCollections.addLabWork(labWork)
//    println(labWorkCollections.getCountElements())
//    println(labWorkCollections.getCollections())
//    labWorkCollections.groupMinPoint().forEach { (key, value) ->
//        println(key)
//        println(labWork)
//        value.forEach{ println(LabWorkToString(it).labWorkToString(true)) }
//    }
//    println(LabWorkToString(labWork).labWorkToString(csv = true))
//    println(labWorkCollections.labWorkCollections)
//    lateinit var listFromFile: ListFromFile
//    var flag = false
//    try {
//        val systemPathToRoad: String = SystemPathToRoad("JavaOut1").path
//        val listToFile = ListToFile(systemPathToRoad, HashSetToList(labWorkCollections.labWorkCollections).list, 1)
//        listToFile.fileWrite()
//        System.`in`.read()
//        listFromFile = ListFromFile(systemPathToRoad)
//        flag = true
//        listFromFile.fileRead()
//    } catch (e: RuntimeException) {
//        System.err.println(e.message)
//    }
//    if (flag) {
//        println(listFromFile.list)
//    }

}

