package com.company

import com.company.classes.Discipline
import com.company.classes.Coordinates


fun main() {
    val discipline = Discipline()
    print("input coordinates: ")
    try {
        val coordinates = Coordinates(readLine().toString())
        println(coordinates.getCoordinates())
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }


}