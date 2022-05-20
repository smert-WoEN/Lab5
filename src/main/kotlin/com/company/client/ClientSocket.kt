package com.company.client

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket


class ClientSocket(private val ip: String, private val port: Int) {
    private val socket = Socket()
    private lateinit var serverSocketAddress: InetSocketAddress
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    @Throws(IOException::class)
    fun initialize() {
        serverSocketAddress = InetSocketAddress(ip, port)
        socket.connect(serverSocketAddress)
        inputStream = socket.getInputStream()
        outputStream = socket.getOutputStream()
    }

    @Throws(IOException::class)
    fun sendMessage(any: Any) {
//        val obj = ObjectOutputStream(outputStream)
//        obj.writeObject(any)
//        obj.flush()
        val byteArrayOutputStream = ByteArrayOutputStream(1024*1024)
        val obj = ObjectOutputStream(byteArrayOutputStream)
        obj.writeObject(any)
        obj.flush()
        byteArrayOutputStream.writeTo(outputStream)
    }
    @Throws(IOException::class)
    fun readMessage(): Any {
        val objectInputStream = ObjectInputStream(inputStream)
        return objectInputStream.readObject()
    }

    fun disconnect() {
        socket.close()
    }
}