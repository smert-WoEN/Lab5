package com.company.collection

import com.company.superclasses.Difficulty
import java.util.Date

data class LabWork(val id: Int, val name: String, val coordinates: Coordinates,
                   val creationDate: Date, val minimalPoint: Int,
                   val maximalPoint: Double, val difficulty: Difficulty,
                   val discipline: Discipline)
