package com.company.collection

import com.company.collection.ValidatorLabWork.LabWorkValidateConfig.maxMaximalPoint
import com.company.collection.ValidatorLabWork.LabWorkValidateConfig.maxMinimalPoint
import com.company.collection.ValidatorLabWork.LabWorkValidateConfig.minMaximalPoint
import com.company.collection.ValidatorLabWork.LabWorkValidateConfig.minMinimalPoint
import com.company.legacy.LabWork
import com.company.superclasses.Difficulty
import com.company.superclasses.LabWorkValidate
import java.util.*

class ValidatorLabWork: LabWorkValidate {

    internal object LabWorkValidateConfig {
        var minMinimalPoint = 0
        var maxMinimalPoint = Int.MAX_VALUE
        var minMaximalPoint = 0
        var maxMaximalPoint = Double.MAX_VALUE
    }
    override fun checkName(name: String): String {
        if (name.isBlank()){
            throw IllegalArgumentException("name empty")
        }
        else {
            return name
        }
    }

    override fun checkCreationDate(creationDate: Date): Date {
        if (creationDate.before(Date())){
            return creationDate
        }
        else {
            throw IllegalArgumentException("date from future")
        }
    }

    override fun checkMinimalPoint(minimalPoint: Int): Int {
        if (minimalPoint in (minMinimalPoint + 1) until maxMinimalPoint) {
            return minimalPoint
        }
        else {
            throw IllegalArgumentException("minimalPoint out of range [$minMinimalPoint, $maxMinimalPoint]")
        }
    }

    override fun checkMaximalPoint(maximalPoint: Double): Double {
        if (minMaximalPoint < maximalPoint && maximalPoint < maxMaximalPoint) {
            return  maximalPoint
        }
        else {
            throw IllegalArgumentException("maximalPoint out of range [$minMaximalPoint, $maxMaximalPoint]")
        }
    }

    override fun checkLabWork(
        id: Int,
        name: String,
        coordinates: Coordinates,
        creationDate: Date,
        minimalPoint: Int,
        maximalPoint: Double,
        difficulty: Difficulty,
        discipline: Discipline
    ): LabWork {
        return LabWork(id, checkName(name), coordinates, checkCreationDate(creationDate), checkMinimalPoint(minimalPoint), checkMaximalPoint(maximalPoint), difficulty, discipline)
    }
}