package com.company.client

import com.company.collection.Coordinates
import com.company.collection.Discipline
import com.company.collection.LabWork
import com.company.superclasses.Difficulty
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.*

fun main() {
    val port = 25566
    val ip = "127.0.0.1"
    try {
        val clientSocket = Socket(ip, port)
        val outputStream = clientSocket.getOutputStream()
        val inputStream = clientSocket.getInputStream()
        val objectOutputStream = ObjectOutputStream(outputStream)
        val objectInputStream = ObjectInputStream(inputStream)
        val labWork = LabWork(1, " 2", Coordinates(5, 4.0), Date(), 5, 5.5, Difficulty.EASY,
        Discipline("asd", null, 2, 2, 2))
        println("ok lab")
        objectOutputStream.writeObject(labWork)
        println("send")
    } catch (e: IOException) {
        System.err.println(e.message)
    }
}