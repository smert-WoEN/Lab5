package com.company.collection

import com.company.collection.ValidatorCoordinates.CoordinatesValidatorConfig.maxX
import com.company.collection.ValidatorCoordinates.CoordinatesValidatorConfig.maxY
import com.company.collection.ValidatorCoordinates.CoordinatesValidatorConfig.minX
import com.company.collection.ValidatorCoordinates.CoordinatesValidatorConfig.minY
import com.company.superclasses.CoordinatesValidate

class ValidatorCoordinates: CoordinatesValidate {
    internal object CoordinatesValidatorConfig{
        var minX = -497
        var maxX = Int.MAX_VALUE
        var minY = Double.MIN_VALUE
        var maxY = 268
    }

    override fun checkX(x: Int): Int {
        if (x in (minX + 1) until maxX) {
            return x
        }
        else {
            throw IllegalArgumentException("x out of range [$minX, $maxX]")
        }
    }

    override fun checkY(y: Double): Double {
        if (minY < y && y < maxY) {
            return  y
        }
        else {
            throw IllegalArgumentException("y out of range [$minY, $maxY]")
        }
    }

    override fun checkCoordinates(x: Int, y: Double): Coordinates {
        return Coordinates(checkX(x), checkY(y))
    }
}