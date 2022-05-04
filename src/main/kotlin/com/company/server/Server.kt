package com.company.server

import com.company.collection.LabWork
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

fun main() {
    val ip = ""
    val port = 25566
    try{
        val server = ServerSocketChannel.open()
        server.configureBlocking(false)
        server.socket().bind(InetSocketAddress(ip, port))
        lateinit var serverSocket: SocketChannel
        println("start ok")
        while (true) {
            val serverSocketLoc = server.accept()
            if (serverSocketLoc != null) {
                serverSocket = serverSocketLoc
                break
            }
        }
        println(serverSocket)
        val objectInputStream = ObjectInputStream(serverSocket.socket().getInputStream())
        val objectOutputStream = ObjectOutputStream(serverSocket.socket().getOutputStream())
        println("stream ok")
        val some = objectInputStream.readObject()
        println("some")
        println(some)
    } catch (e: IOException) {
        System.err.println(e.message)
    }

}