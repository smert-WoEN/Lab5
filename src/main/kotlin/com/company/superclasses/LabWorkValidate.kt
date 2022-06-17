package com.company.superclasses

import com.company.collection.Coordinates
import com.company.collection.Discipline
import com.company.legacy.LabWork
import java.util.Date

interface LabWorkValidate {
    fun checkName(name: String): String
    fun checkCreationDate(creationDate: Date): Date
    fun checkMinimalPoint(minimalPoint: Int): Int
    fun checkMaximalPoint(maximalPoint: Double): Double
    fun checkLabWork(id: Int, name: String, coordinates: Coordinates, creationDate: Date, minimalPoint: Int, maximalPoint: Double, difficulty: Difficulty, discipline: Discipline): LabWork
}