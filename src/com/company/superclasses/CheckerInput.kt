package com.company.superclasses

import java.io.PrintStream
import java.util.*

abstract class CheckerInput {
    lateinit var printStream: PrintStream
    lateinit var scanner: Scanner

    open fun initialize() {}

    fun initialize(printStream: PrintStream, scanner: Scanner) {
        this.printStream = printStream
        this.scanner = scanner
        initialize()
    }

    open fun checkString() {}
}