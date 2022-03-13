package com.company.superclasses

import com.company.collection.Discipline

interface DisciplineValidate {
    fun checkName(name: String): String
    fun checkLectureHours(lectureHours: Long?): Long?
    fun checkPracticeHours(practiceHours: Int): Int
    fun checkSelfStudyHours(selfStudyHours: Long): Long
    fun checkLabsCount(labsCount: Int): Int
    fun checkDiscipline(name: String, lectureHours: Long?, practiceHours: Int, selfStudyHours: Long, labsCount: Int): Discipline
}