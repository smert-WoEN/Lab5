package com.company.files

import com.company.collection.LabWork
import org.apache.commons.codec.digest.DigestUtils
import java.io.BufferedWriter
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException

class ListToFile(private val name: String, private val list: ArrayList<LabWork>, private val id: Int) {

    fun fileWrite() {
        try {
            BufferedWriter(FileWriter(name)).use { writer ->
                writer.write(id.toString() + ", " + DigestUtils.sha512Hex(id.toString()) + "\n")
                println(name)
                for (labWork in list) {
                    val string = LabWorkToString(labWork).labWorkToString(csv = true)
                    writer.write(string + ", " + DigestUtils.sha512Hex(string) + "\n")
                }
            }
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Can't find file in path.")
        } catch (e: SecurityException) {
            throw IllegalArgumentException("Can't access file in path.")
        } catch (e: IOException) {
            throw IllegalArgumentException("Error occurred accessing file in path.")
        }
    }
}