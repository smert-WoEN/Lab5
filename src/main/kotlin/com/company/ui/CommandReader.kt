package com.company.ui

import java.io.BufferedReader
import java.io.IOException
import com.company.ui.CommandReader.UserCommand
import java.io.InputStreamReader
import java.util.Locale
import java.util.Scanner

/**
 * Class designed to get commands from buffered readers and strings
 *
 * @see UserRunnable
 */
class CommandReader
/**
 * Command reader constructor
 *
 * @param bufferedReader buffered reader to get commands from
 */(val scanner: Scanner) {
    /**
     * Gets a string from buffered reader line
     *
     * @return Received string
     */
    val stringFromBufferedReader: String
        get() = try {
            scanner.nextLine()
        } catch (e: IOException) {
            println("Error: " + e.message + ".")
            ""
        }

    /**
     * Get a command from Buffered Reader
     *
     * @return Command
     */
    fun readCommandFromBufferedReader(): UserCommand {
        return readCommandFromString(stringFromBufferedReader)
    }

    /**
     * User command class
     */
    class UserCommand {
        lateinit var Command: String
        lateinit var Argument: String

        /**
         * User command constructor with argument
         *
         * @param Command  Command
         * @param Argument Argument
         */
        constructor(Command: String, Argument: String) {
            this.Command = Command
            this.Argument = Argument
        }

        /**
         * User command constructor without argument
         *
         * @param Command Command
         */
        constructor(Command: String) {
            this.Command = Command
        }

        /**
         * Empty user command constructor
         */
        constructor() {}

        override fun toString(): String {
            return "Command{" +
                    "Command='" + Command + '\'' +
                    ", Arguments='" + Argument + '\'' +
                    '}'
        }
    }

    /**
     * Get an array of strings from System.in (separated by spaces)
     *
     * @return Array of strings
     */
    val stringsFromTerminal: Array<String>
        get() = CommandReader(scanner).stringFromBufferedReader.split(" ".toRegex())
            .toTypedArray()

    /**
     * Get a command from string
     *
     * @param singleString string to parse from
     * @return Command
     */
    fun readCommandFromString(singleString: String): UserCommand {
        return readCommandFromString(singleString.split(" ".toRegex(), 2).toTypedArray())
    }

    /**
     * Get a command from strings
     *
     * @param input strings to parse from
     * @return Command
     */
    fun readCommandFromString(input: Array<String>): UserCommand {
        return if (input.size != 0) {
            input[0] = input[0].lowercase(Locale.getDefault())
            if (input.size > 1) UserCommand(input[0], input[1]) else UserCommand(input[0])
        } else UserCommand()
    }

}