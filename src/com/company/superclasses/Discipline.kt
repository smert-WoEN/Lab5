package com.company.superclasses

import kotlin.properties.Delegates

interface Discipline {
        fun getDiscipline(cvs: Boolean = false): String
        var name: String
        var lectureHours: Long?
        var practiceHours: Int
        var selfStudyHours: Long
        var labsCount: Int
}