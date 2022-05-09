package com.company.server

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

object ServerTest {
    private var selector: Selector? = null
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            selector = Selector.open()
            //            We have to set connection host, port and non-blocking mode
            val socket = ServerSocketChannel.open()
            val serverSocket = socket.socket()
            serverSocket.bind(InetSocketAddress("localhost", 25566))
            socket.configureBlocking(false)
            val ops = socket.validOps()
            socket.register(selector, ops, null)
            while (true) {
                selector!!.select()
                val selectedKeys = selector!!.selectedKeys()
                val i = selectedKeys.iterator()
                while (i.hasNext()) {
                    val key = i.next()
                    if (key.isAcceptable) {
//                        New client has been accepted
                        handleAccept(socket, key)
                    } else if (key.isReadable) {
//                        We can run non-blocking operation READ on our client
                        handleRead(key)
                    }
                    i.remove()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun handleAccept(
        mySocket: ServerSocketChannel,
        key: SelectionKey
    ) {
        println("Connection Accepted...")

        // Accept the connection and set non-blocking mode
        val client = mySocket.accept()
        client.configureBlocking(false)

        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ)
    }

    @Throws(IOException::class)
    private fun handleRead(key: SelectionKey) {
        println("Reading...")
        // create a ServerSocketChannel to read the request
        val client = key.channel() as SocketChannel

        // Create buffer to read data
        val buffer = ByteBuffer.allocate(1024)
        client.read(buffer)
        //        Parse data from buffer to String
        val data = String(buffer.array()).trim { it <= ' ' }
        if (data.isNotEmpty()) {
            println("Received message: $data")
            if (data.equals("exit", ignoreCase = true)) {
                client.close()
                println("Connection closed...")
            }
        }
    }
}