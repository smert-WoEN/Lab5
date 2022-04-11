package com.company

import com.company.collection.*
import com.company.commands.Add
import com.company.commands.Command
import com.company.files.*
import com.company.superclasses.Difficulty
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val scanner = Scanner(FileInputStream(File("D:\\intel\\untitled5\\src\\main\\kotlin\\com\\company\\s.txt")))
    scanner.forEach { println(it) }
//    val printStream = System.out
//    val errorStream = System.err
//    val scanner = Scanner(System.`in`)
//    val pathName = "JavaOut1"
//    try {
//        val pathToRoad = SystemPathToRoad(pathName).path
//        val labWorkCollections = LabWorkCollections()
//        val labWorkCreator = LabWorkCreator(printStream, errorStream, scanner)
//    } catch (e: IllegalArgumentException) {
//        errorStream.println(e.message + " program can't load.")
//    }



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

