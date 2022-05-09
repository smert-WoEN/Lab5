package com.company.server

import com.company.collection.LabWork
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.Scanner

var status = true
var ssda = "sa"
var readConsole = Runnable {
    val s = scanner.nextLine()
    println(s)
    if (s == "exit") {
        status = false
        ssda = "stop"
    }
}
var readConsoleUpdate = Thread(readConsole)
lateinit var scanner: Scanner
fun main() {
    scanner = Scanner(System.`in`)
    val ip = ""
    val port = 25566
    try{
        val serverSocketChannel = ServerSocketChannel.open()
        serverSocketChannel.configureBlocking(false)
        val serverSocket = serverSocketChannel.socket()
        serverSocket.bind(InetSocketAddress(ip, port))
        val selector: Selector = Selector.open()
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
        println("start ok")
        //readConsoleUpdate.start()
        while (true) {
            val count = selector.select()
            if (count > 0) {
                val iterator = selector.selectedKeys().iterator()
                while (iterator.hasNext()) {
                    val key = iterator.next()
                    if (key.isAcceptable) {
                        iterator.remove();
                        val serverSocketChannel1 = key.channel() as ServerSocketChannel
                        val socketChannel = serverSocketChannel1.accept()
                        socketChannel.configureBlocking(false)
                        socketChannel.register(selector, SelectionKey.OP_READ)
                        println("add")
                    }
                    if (key.isValid && key.isReadable) {
                        val channel: SocketChannel = key.channel() as SocketChannel
                        val buffer = ByteBuffer.allocate(1024*1024)
                        channel.read(buffer)
                        println(buffer.array())
                        val byteArrayInputStream = ByteArrayInputStream(buffer.array())
                        val objectInputStream = ObjectInputStream(byteArrayInputStream)
                        val obj = objectInputStream.readObject()
                        println(obj)
//                        println(count1)
//                        //val obj = ObjectInputStream(ByteBufferBackedInputStream(buffer))
//                        //println(obj)
//                        println(count1)
//                        println(buffer)
//                        if (count1 > 0) {
//                            buffer.flip()
//                            val s = (buffer.array())
//                            val obj = ObjectInputStream(ByteArrayInputStream(s)).readObject()
//                            println(obj)
//                        }
                        //val obj = ObjectInputStream(channel.socket().getInputStream()).readObject()
                        println(key.isValid && key.isReadable)
                        println(iterator)
                        println(key.toString())
                    }
                }
            }
        }

    //        Scanner(System.`in`).nextLine()
//        while (true) {
//            val serverSocketLoc = server.accept()
//            if (serverSocketLoc != null) {
//                serverSocket = serverSocketLoc
//                break
//            }
//        }
//        println(serverSocket)
//        val buffer = ByteBuffer.allocate(32 * 32)
//        println(buffer)
//        buffer.clear()
//        Scanner(System.`in`).nextLine()
//        serverSocket.read(buffer)
//        println(buffer)
//        val objectInputStream = ObjectInputStream(serverSocket.socket().getInputStream())
//        val objectOutputStream = ObjectOutputStream(serverSocket.socket().getOutputStream())
//        println("stream ok")
//        val some = objectInputStream.readObject()
//        println("some")
//        println(some)
    } catch (e: IOException) {
        System.err.println(e)
    }

}