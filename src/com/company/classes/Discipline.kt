package com.company.classes

import com.company.superclasses.Discipline
import java.util.regex.PatternSyntaxException
import kotlin.properties.Delegates

class Discipline : Discipline {
    override lateinit var name: String
    override var lectureHours: Long? = null
    override var practiceHours by Delegates.notNull<Int>()
    override var selfStudyHours by Delegates.notNull<Long>()
    override var labsCount by Delegates.notNull<Int>()
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
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
        this.name = "s"
    }

    constructor(name: String, lectureHours: Long?, practiceHours: Int, selfStudyHours: Long, labsCount: Int) {
        this.name = name
    }

    constructor(){

    }
    override fun getDiscipline(cvs: Boolean): String {
        TODO("Not yet implemented")
    }


}