package com.company.client

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

object TestClient {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val messages =
                arrayOf("I like non-blocking servers", "Hello non-blocking world!", "One more message..", "exit")
            println("Starting client...")
            val client = SocketChannel.open(InetSocketAddress("localhost", 25566))
            for (msg in messages) {
                println("Prepared message: $msg")
                val buffer = ByteBuffer.allocate(1024)
                buffer.put(msg.toByteArray())
                buffer.flip()
                val bytesWritten = client.write(buffer)
                System.out.printf("Sending Message: %s\nbufforBytes: %d%n", msg, bytesWritten)
            }
            client.close()
            println("Client connection closed")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}