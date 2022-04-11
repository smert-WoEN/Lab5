package com.company.commands

import java.util.Scanner

class CommandReader(private val scanner: Scanner) {

    fun readCommandFromString(string: String): List<String> {
        return string.split(" ", limit = 2)
    }

}