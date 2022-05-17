package com.company.client

import com.company.client.ui.ClientRunnable
import com.company.collection.LabWorkCreatorClient
import java.io.IOException
import java.util.*

fun main() {
    val port = 25566
    val printStream = System.out
    val errorStream = System.err
    val scanner = Scanner(System.`in`)
    val ip = "127.0.0.1"
    try{
        val clientSocket = ClientSocket(ip, port)
        clientSocket.initialize()
        val labWorkCreatorClient = LabWorkCreatorClient(printStream, errorStream, scanner)
        val clientRunnable = ClientRunnable(clientSocket, labWorkCreatorClient, printStream, errorStream, scanner)
        clientRunnable.run()
    } catch (e: RuntimeException){
        e.printStackTrace()
    }  catch (e: IOException) {
        errorStream.println(e.message)
    }

//    val socket = Socket()
//    val serverSocketAddress = InetSocketAddress(ip, port)
//    socket.connect(serverSocketAddress)
//    val inputStream = socket.getInputStream()
//    val outputStream = socket.getOutputStream()
//
//    val labWork = LabWork(1, " 2", Coordinates(5, 4.0), Date(), 5, 5.5, Difficulty.EASY,
//        Discipline("asd", null, 2, 2, 2))
//
//    Scanner(System.`in`).nextLine()
//    for (i in 1..10) {
//        val byteArrayOutputStream = ByteArrayOutputStream(1024)
//        val obj = ObjectOutputStream(byteArrayOutputStream)
//        obj.writeObject(labWork)
//        obj.flush()
//        byteArrayOutputStream.writeTo(outputStream)
//        //outputStream.write(byteArrayOutputStream.toByteArray())
//        val byte = byteArrayOutputStream.toByteArray()
//        byteArrayOutputStream.reset()
//        // Scanner(System.`in`).nextLine()
//        val objectInputStream: ObjectInputStream = ObjectInputStream(inputStream)
//        val obj1 = objectInputStream.readObject()
//        println(obj1)
//    }
//    Scanner(System.`in`).nextLine()
}