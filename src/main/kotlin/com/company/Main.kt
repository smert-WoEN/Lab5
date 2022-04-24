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

}

