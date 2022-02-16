package com.company.superclasses

import kotlin.properties.Delegates


interface Coordinates {
    var x: Int
    var y: Double
    fun getCoordinates(csv: Boolean = false) : String
}