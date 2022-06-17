package com.company.server

import com.company.SocketCloseException
import org.apache.logging.log4j.Logger
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

class SomeClient(private val logger: Logger, private val ipAddress: String, private val socket: SocketChannel, private val key: SelectionKey,
private var bufferIn: ByteBuffer = ByteBuffer.allocate(1024 * 1024),
                 private var bufferOut: ByteBuffer = ByteBuffer.allocate(1024 * 1024)) {
    //var bufferIn: ByteBuffer = ByteBuffer.allocate(1024)
    ///var bufferOut: ByteBuffer = ByteBuffer.allocate(1024)
    lateinit var token: Token
    fun receiveMessage(): Any {
        logger.info("receive message")
        //bufferIn.clear()
        val bytesIn: Int = socket.read(bufferIn)
        if (bytesIn == -1) {
            throw SocketCloseException("Socket closed")
        }
        var byteArray = ByteArray(0)
        if (bytesIn > 0) {

            bufferIn.flip()
            bufferIn.mark()
            //byteArray = bufferIn.array()
            if (bufferIn.hasRemaining()) {
//                byteArray = ByteArray(1)
//                byteArray[0] = bufferIn.get()
                while (bufferIn.hasRemaining()) {
                    byteArray += bufferIn.get() //bufferIn.get()
                }
            }
            bufferIn.compact()
        }
        val byteArrayInputStream: InputStream = ByteArrayInputStream(byteArray)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        return objectInputStream.readObject()
    }

    fun sendMess(any: Any) {
        logger.info("send message")
        val byteArrayOutputStream = ByteArrayOutputStream(1024 * 1024)
        val obj = ObjectOutputStream(byteArrayOutputStream)
        obj.writeObject(any)
        obj.flush()
        val byteArray = byteArrayOutputStream.toByteArray()
        bufferOut.put(byteArray)
    }

    fun sendMessage(): Int {
        bufferOut.flip()
        val bytesOut = socket.write(bufferOut)
        bufferOut.compact()
        if (bufferOut.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ or SelectionKey.OP_WRITE)
        } else {
            key.interestOps(SelectionKey.OP_READ)
        }
        return bytesOut
    }

    fun disconnect() {
        try {
            logger.info("$ipAddress disconnect")
            socket.close()
            key.cancel()
        } catch (e: IOException) {
            logger.error(e)
            //e.printStackTrace()
        }
    }
}
