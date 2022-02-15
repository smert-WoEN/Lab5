package com.company.superclasses

import java.util.*

interface Discipline {
    var name: String
    var lectureHours: Long?
    var practiceHours: Int
    var selfStudyHours: Long
    var labsCount: Int

    fun setName(console: Boolean = true){}
}