package com.company.server

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel


object ServerNonBlock {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val serverSocketChannel: ServerSocketChannel = ServerSocketChannel.open()
        serverSocketChannel.configureBlocking(false)
        serverSocketChannel.bind(InetSocketAddress(25566))
        while (true) {
            val socketChannel: SocketChannel? = serverSocketChannel.accept() // non-blocking
            if (socketChannel != null) {
                socketChannel.configureBlocking(false)
                val buffer: ByteBuffer = ByteBuffer.allocate(1024)
                while (true) {
                    buffer.clear()
                    val read: Int = socketChannel.read(buffer) // non-blocking
                    if (read < 0) {
                        break
                    }
                    buffer.flip()
                    socketChannel.write(buffer) // can be non-blocking
                }
                socketChannel.close()
            }
        }
        serverSocketChannel.close()
    }
}