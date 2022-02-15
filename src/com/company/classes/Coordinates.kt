package com.company.classes

import java.util.regex.PatternSyntaxException
import kotlin.NumberFormatException
import kotlin.properties.Delegates

class Coordinates : Comparable<Coordinates>, com.company.superclasses.Coordinates {

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
                this.x = arguments[0].toInt()
                this.y = arguments[1].toDouble()
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.message + ".")
        }
    }

    override fun getCoordinates(csv: Boolean): String {
        TODO("Not yet implemented")
    }

    override fun compareTo(other: Coordinates): Int {
        TODO("Not yet implemented")
    }

}