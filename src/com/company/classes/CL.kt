package com.company.classes

import java.lang.IllegalArgumentException
import java.util.regex.PatternSyntaxException
import java.lang.NullPointerException

class CL {
    constructor(fromString: String) {
        val arguments: Array<String>
        arguments = try {
            fromString.split(" ".toRegex(), 2).toTypedArray()
        } catch (e: PatternSyntaxException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Error in argument: " + e.message + ".")
        }
    }

    constructor() {}
}