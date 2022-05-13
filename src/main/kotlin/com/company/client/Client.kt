package com.company.client

import com.company.collection.Coordinates
import com.company.collection.Discipline
import com.company.collection.LabWork
import com.company.superclasses.Difficulty
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.ByteBuffer
import java.util.*

fun main() {
    val port = 25566
    val ip = "127.0.0.1"
    val socket = Socket()
    val serverSocketAddress = InetSocketAddress(ip, port)
    socket.connect(serverSocketAddress)

        val labWork = LabWork(1, " 2", Coordinates(5, 4.0), Date(), 5, 5.5, Difficulty.EASY,
        Discipline("asd", null, 2, 2, 2))
    Scanner(System.`in`).nextLine()
    val outputStream = socket.getOutputStream()
    val byteArrayOutputStream = ByteArrayOutputStream(1024)
    val obj = ObjectOutputStream(byteArrayOutputStream)
    obj.writeObject(labWork)
    obj.flush()
    //byteArrayOutputStream.writeTo(outputStream)
    outputStream.write(byteArrayOutputStream.toByteArray())
    val byte = byteArrayOutputStream.toByteArray()
    for (a in byte) {
        print("$a ")
    }
    byteArrayOutputStream.reset()
    Scanner(System.`in`).nextLine()

    //ObjectOutputStream(ByteBufferBackedOutputStream(buffer)).writeObject(labWork)
        //println("send")
//    } catch (e: IOException) {
//        System.err.println(e.message)
//    }
}