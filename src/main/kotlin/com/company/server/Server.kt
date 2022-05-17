package com.company.server

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.files.ListFromFile
import com.company.files.ListToHashSet
import com.company.files.SystemPathToRoad
import com.company.server.ui.ServerRunnable
import java.util.*

fun main() {
    val port = 25566
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

        val serverRunnable = ServerRunnable(port, labWorkCollections, labWorkCreator, pathToRoad, printStream, errorStream, scanner)
        serverRunnable.initialize()
        serverRunnable.run()
    } catch (e: IllegalArgumentException) {
        errorStream.println(e.message + " program can't load.")
    }
}