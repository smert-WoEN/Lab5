package com.company.server

import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer

class ByteBufferBackedInputStream(var buf: ByteBuffer) : InputStream() {
    @Throws(IOException::class)
    override fun read(): Int {
        return if (!buf.hasRemaining()) {
            -1
        } else buf.get().toInt() and 0xFF
    }

    @Throws(IOException::class)
    override fun read(bytes: ByteArray, off: Int, len: Int): Int {
        var len = len
        if (!buf.hasRemaining()) {
            return -1
        }
        len = len.coerceAtMost(buf.remaining())
        buf[bytes, off, len]
        return len
    }
}