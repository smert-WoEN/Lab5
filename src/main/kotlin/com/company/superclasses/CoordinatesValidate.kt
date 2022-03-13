package com.company.superclasses

import com.company.collection.Coordinates

interface CoordinatesValidate {
    fun checkX(x: Int): Int
    fun checkY(y: Double): Double
    fun checkCoordinates(x: Int, y: Double): Coordinates
}