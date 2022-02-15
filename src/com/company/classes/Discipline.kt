package com.company.classes

import com.company.superclasses.Discipline
import java.io.PrintStream
import java.util.*

class Discipline(): Discipline {
    override lateinit var name: String
    override var lectureHours: Long? = null
    override var practiceHours: Int = -1
    override var selfStudyHours: Long = -1
    override var labsCount: Int = -1


}