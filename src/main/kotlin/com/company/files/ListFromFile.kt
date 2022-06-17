package com.company.files

import com.company.collection.*
import com.company.legacy.LabWork
import com.company.superclasses.Difficulty
import com.company.superclasses.FromFile
import org.apache.commons.codec.digest.DigestUtils
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class ListFromFile (private val name: String): FromFile {
    override val list = ArrayList<LabWork>()
    override var id = 0
        private set

    override var count: UInt = 0u
        private set
    private var maxId = 0
    private var stringError = ""
    fun fileRead() {
        try {
            val file = FileReader(name)
            val array: ArrayList<String> = file.readLines() as ArrayList<String>
            file.close()
            if (array.size > 1){
                val firstString = array[0]
                array.remove(firstString)
                val firstStringArray = firstString.split(",")
                if (firstStringArray[1].trim() != DigestUtils.sha512Hex(firstStringArray[0].trim())) {
                    stringError = ("This file cannot be loaded because it has been modified.")
                } else {
                    id = firstStringArray[0].trim().toInt()
                    for (string in array) {
                        val reverseS = string.reversed()
                        val arrayS = reverseS.split(",", limit = 2)
                        val string1 = arrayS[1].reversed().trim()
                        val hashCodeString = arrayS[0].reversed().trim()

                        if (DigestUtils.sha512Hex(string1) != hashCodeString) {
                            count++
                        } else {
                            try {
                                val validatorLabWork = ValidatorLabWork()
                                val validatorCoordinates = ValidatorCoordinates()
                                val validatorDiscipline = ValidatorDiscipline()

                                val values = string1.split(",", limit = 13)
                                val labWork = validatorLabWork.checkLabWork(
                                    (values[0].trim()).toInt() ,
                                    (values[1].trim()),
                                    validatorCoordinates.checkCoordinates(values[2].trim().toInt(), values[3].trim().toDouble()),
                                    Date(values[4].trim().toLong()),
                                    values[5].trim().toInt(),
                                    values[6].trim().toDouble(),
                                    Difficulty.valueOf(values[7].trim()),
                                    validatorDiscipline.checkDiscipline(
                                        values[8].trim(),
                                        if (values[9].trim() == "null") null else values[9].trim().toLong(),
                                        values[10].trim().toInt(),
                                        values[11].trim().toLong(),
                                        values[12].trim().toInt()
                                    )
                                )
                                list.add(labWork)
                                if (values[0].trim().toInt() > maxId) {
                                    maxId = values[0].trim().toInt()
                                }
                            } catch (e: IllegalArgumentException) {
                                count++
                            }
                        }
                    }
                    if (maxId > id) {
                        id = maxId
                    }
                    if (count != 0u) {
                        stringError = ("From this file cannot be loaded $count labs, because it has been modified.")
                    }
                }
            }
        }
        catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Can't find file in path.")
        } catch (e: SecurityException) {
            throw IllegalArgumentException("Can't access file in path.")
        } catch (e: IOException) {
            throw IllegalArgumentException("Error occurred accessing file in path.")
        }
        if (stringError.isNotBlank()) {
            throw NumberFormatException(stringError)
        }
    }

}