package com.company.server

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.files.ListFromFile
import com.company.files.ListToHashSet
import com.company.files.SystemPathToRoad
import com.company.server.ui.ServerRunnable
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*
fun main() {
    val port = 25566
    val printStream = System.out
    val errorStream = System.err
    val scanner = Scanner(System.`in`)
    val pathName = "JavaOut"
    val logger: Logger = LogManager.getLogger()
    logger.info("start0")
    logger.warn("start")
    logger.error("start program")
    logger.debug("start2")


    //val logMaker = LogMaker()
    try {
        //logMaker.info("Start logging")
        val pathToRoad = SystemPathToRoad(pathName).path
        val labWorkCollections = LabWorkCollections()
        lateinit var labWorkCreator: LabWorkCreator
        try {
            val listFromFile = ListFromFile(pathToRoad)
            try {
                listFromFile.fileRead()
            } catch (e: NumberFormatException) {
                logger.error(e)
                //errorStream.println(e.message)
            }
            labWorkCreator = LabWorkCreator(printStream, errorStream, scanner, listFromFile.id)
            labWorkCollections.setFromList(ListToHashSet(listFromFile.list).hashSet)
        } catch (e: IllegalArgumentException) {
            logger.error(e)
            //errorStream.println(e.message)
            labWorkCreator = LabWorkCreator(printStream, errorStream, scanner)
        }

        val serverRunnable = ServerRunnable(port, labWorkCollections, labWorkCreator, pathToRoad, printStream, errorStream, scanner, logger)
        serverRunnable.initialize()
        serverRunnable.run()
    } catch (e: IllegalArgumentException) {
        logger.fatal(e)
        //errorStream.println(e.message + " program can't load.")
    }
}