package com.company.classes

import com.company.superclasses.Discipline
import java.io.PrintStream
import java.util.*
import java.util.regex.PatternSyntaxException
import kotlin.properties.Delegates

class Discipline: Discipline {
    private lateinit var name: String
    private var lectureHours: Long? = null
    private var practiceHours by Delegates.notNull<Int>()
    private var selfStudyHours by Delegates.notNull<Long>()
    private var labsCount by Delegates.notNull<Int>()
    private lateinit var arguments: Array<String>

    constructor(argument: String) {
        val arguments: Array<String>
        try {
            arguments = argument.split(" ", limit = 5).toTypedArray()
        } catch (e: PatternSyntaxException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
        try {
            this.arguments = arguments
            setName(arguments[0])
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
    }

    constructor() {

    }

    override fun getDiscipline(cvs: Boolean): String {
        TODO("Not yet implemented")
    }


    private fun setName(string: String) {
        if (string.isNotEmpty()) {
            this.name = string
        }
        else {
            throw IllegalArgumentException("Empty string, try again")
        }
    }

}