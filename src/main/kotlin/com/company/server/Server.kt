package com.company.server

import com.company.collection.LabWork
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.ObjectInput
import java.io.ObjectInputStream
import java.net.InetSocketAddress
import java.nio.channels.SelectionKey
import java.nio.channels.ServerSocketChannel
import java.nio.channels.spi.SelectorProvider
import java.util.Iterator

@Throws(IOException::class)
fun accept(key: SelectionKey) {
    val channel = key.channel() as ServerSocketChannel
    val socket = channel.accept()
    val ipAddress = socket.socket().inetAddress.hostAddress
    println("connected from $ipAddress")
    socket.configureBlocking(false)
    val k = socket.register(key.selector(), SelectionKey.OP_READ or SelectionKey.OP_WRITE)
    k.interestOps(SelectionKey.OP_READ)
    k.attach(SomeClient(ipAddress, socket, k))
    println("connection add")
}
fun main() {
    val ip = ""
    val port = 25566
    try{
        val acceprot = ServerSocketChannel.open()
        acceprot.configureBlocking(false)
        acceprot.socket().bind(InetSocketAddress(ip, port))
        acceprot.socket().reuseAddress = true
        val selector = SelectorProvider.provider().openSelector()
        val acceptKey = acceprot.register(selector, SelectionKey.OP_ACCEPT)
        acceptKey.interestOps(SelectionKey.OP_ACCEPT)
        while (true) {
            selector.select()
            val iterator = selector.selectedKeys().iterator() as Iterator<SelectionKey>
            while (iterator.hasNext()) {
                val key = iterator.next()
                iterator.remove()
                if (!key.isValid){
                    continue
                }
                val someClient: SomeClient? = key.attachment() as? SomeClient
                if (key.isAcceptable){
                    accept(key)
                }
                if (key.isReadable) {
                    println("ok")
                    val s = someClient!!.receiveMessage()
                    val byteArrayInputStream: InputStream = ByteArrayInputStream(s)
                    println(byteArrayInputStream)
                    val objectInputStream: ObjectInputStream = ObjectInputStream(byteArrayInputStream)
                    val obj = objectInputStream.readObject()
                    println(obj)
                    if (obj is LabWork){
                        println(obj.id)
                    }
                }
            }
        }
//        val serverSocket = serverSocketChannel.socket()
//        serverSocket.bind(InetSocketAddress(ip, port))
//        val selector: Selector = Selector.open()
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
//        println("start ok")
//        //readConsoleUpdate.start()
//        while (true) {
//            val count = selector.select()
//            if (count > 0) {
//                val iterator = selector.selectedKeys().iterator()
//                while (iterator.hasNext()) {
//                    val key = iterator.next()
//                    if (key.isAcceptable) {
//                        iterator.remove();
//                        val serverSocketChannel1 = key.channel() as ServerSocketChannel
//                        val socketChannel = serverSocketChannel1.accept()
//                        socketChannel.configureBlocking(false)
//                        socketChannel.register(selector, SelectionKey.OP_READ)
//                        println("add")
//                    }
//                    if (key.isValid && key.isReadable) {
//                        val channel: SocketChannel = key.channel() as SocketChannel
//                        val buffer = ByteBuffer.allocate(1024*1024)
//                        channel.read(buffer)
//                        println(buffer.array())
//                        val byteArrayInputStream = ByteArrayInputStream(buffer.array())
//                        val objectInputStream = ObjectInputStream(byteArrayInputStream)
//                        val obj = objectInputStream.readObject()
//                        println(obj)
////                        println(count1)
////                        //val obj = ObjectInputStream(ByteBufferBackedInputStream(buffer))
////                        //println(obj)
////                        println(count1)
////                        println(buffer)
////                        if (count1 > 0) {
////                            buffer.flip()
////                            val s = (buffer.array())
////                            val obj = ObjectInputStream(ByteArrayInputStream(s)).readObject()
////                            println(obj)
////                        }
//                        //val obj = ObjectInputStream(channel.socket().getInputStream()).readObject()
//                        println(key.isValid && key.isReadable)
//                        println(iterator)
//                        println(key.toString())
//                    }
//                }
//            }
//        }

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
        e.printStackTrace()
    }

}