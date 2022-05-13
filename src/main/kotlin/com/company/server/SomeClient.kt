package com.company.server

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

class SomeClient(private val ipAddress: String, private val socket: SocketChannel, private val key: SelectionKey,
private var bufferIn: ByteBuffer = ByteBuffer.allocate(2048)) {
    //var bufferIn: ByteBuffer = ByteBuffer.allocate(1024)
    ///var bufferOut: ByteBuffer = ByteBuffer.allocate(1024)

    fun receiveMessage(): ByteArray {
        var bytesIn = 0
        //bufferIn.clear()
        bytesIn = socket.read(bufferIn)
        if (bytesIn == -1) {
            throw IOException("Socket closed")
        }
        println(bytesIn)
        var byteArray: ByteArray = ByteArray(0)
        if (bytesIn > 0) {

            bufferIn.flip()
            bufferIn.mark()
            //byteArray = bufferIn.array()
            if (bufferIn.hasRemaining()) {
//                byteArray = ByteArray(1)
//                byteArray[0] = bufferIn.get()
                while (bufferIn.hasRemaining()) {
                    val s = bufferIn.get()
                    print("$s ")
                    byteArray += s //bufferIn.get()
               }
            }
            bufferIn.compact()
        }
        for (a in byteArray) {
            print("$a ")
        }
        println(byteArray.size)
        return byteArray
    }

    fun disconnect() {
        try {
            socket.close()
            key.cancel()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
