package com.company.classes

import java.util.regex.PatternSyntaxException
import kotlin.NumberFormatException
import kotlin.properties.Delegates

class Coordinates : com.company.superclasses.Coordinates {
    /**
     * Gets x coordinate
     *
     * @return x coordinate
     */
    /**
     * Sets x coordinate
     *
     * @param x new x coordinate
     */
    override var x by Delegates.notNull<Int>()

    /**
     * Sets x coordinate
     *
     * @param y new x coordinate
     */
    /**
     * Gets y coordinate
     *
     * @return y coordinate
     */
    override var y by Delegates.notNull<Double>()

    /**
     * Coordinates from string constructor
     *
     * @param fromString string to parse from
     * @throws IllegalArgumentException if string is invalid
     */
    constructor(fromString: String) {
        val arguments: Array<String> = try {
             fromString.split(" ", limit = 2).toTypedArray()
        } catch (e: PatternSyntaxException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
        try {
            if (arguments[0].toInt() > -497 && arguments[1].toDouble() < 268.0) {
                this.x = arguments[0].toInt()
                this.y = arguments[1].toDouble()
            }
            else {
                throw IllegalArgumentException("Coordinates out from range. Try again")
            }
        }
        catch (e: ArrayIndexOutOfBoundsException) {
            throw IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.message + ".")
        }
        catch (e: NumberFormatException) {
            throw IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.message + ".")
        }
    }

    /**
     * Coordinates constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    constructor(x: Int, y: Double) {
        this.x = x
        this.y = y
    }
    /**
     * Coordinates getter
     *
     * @param csv return string in csv type
     * @throws IllegalArgumentException if coordinates don't set
     */
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