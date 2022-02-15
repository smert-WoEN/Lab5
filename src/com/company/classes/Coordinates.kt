package com.company.classes

import java.util.regex.PatternSyntaxException
import kotlin.NumberFormatException
import kotlin.properties.Delegates

class Coordinates : com.company.superclasses.Coordinates {

    private var x by Delegates.notNull<Int>()
    private var y by Delegates.notNull<Double>()


    constructor(fromString: String) {
        val arguments: Array<String>
        try {
            arguments = fromString.split(" ", limit = 2).toTypedArray()
        } catch (e: PatternSyntaxException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
        try {
            if (arguments.size > 1) {
               if (arguments[0].toInt() > -497 && arguments[1].toDouble() < 268.0) {
                   this.x = arguments[0].toInt()
                   this.y = arguments[1].toDouble()
               }
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.message + ".")
        }
    }

    constructor(x: Int, y: Double) {
        this.x = x
        this.y = y
    }

    override fun getCoordinates(csv: Boolean): String {
        var string: String = ""
        try {
            string = if (csv) {
                "$x, $y"
            } else {
                "$x $y"
            }
        } catch (e: IllegalStateException) {
            throw IllegalArgumentException("Can't get coordinates, because this don't init.")
        }
        return string

    }

}