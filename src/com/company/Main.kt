package com.company

import com.company.classes.Discipline
import com.company.classes.Coordinates


fun main() {
    var discipline = Discipline()
    lateinit var coordinates: Coordinates
    var run = true
    do {
        try {
            print("input coordinates: ")
            coordinates = Coordinates(readLine().toString())
            run = false
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    } while (run)
    try {
        println(coordinates.getCoordinates())
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }

    
}