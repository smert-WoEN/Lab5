package com.company.commands


import com.company.ui.UserRunnable
import java.io.*
import java.util.*

class ExecuteScript(private val userRunnable: UserRunnable,
private val printStream: PrintStream,
private val errorStream: PrintStream): Command {
    override fun getLabel(): String {
        return "executeScript"
    }

    override fun getArgumentLabel(): String {
        return "fileName"
    }

    override fun getDescription(): String {
        return "executes script from \"fileName\""
    }

    override fun execute(argument: String): String {
        val file: File = try {
            File(argument)
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Please specify filename.")
        }
        require(argument.isNotBlank()) { "Please specify filename." }
        try {
            require(file.canRead()) { "Can't read file \"$argument\"." }
        } catch (e: SecurityException) {
            throw IllegalArgumentException("Can't access file \"$argument\".")
        }
        try {
            val fileReader = Scanner(FileInputStream(file))
            fileReader.forEach {
                value ->
                val args = value.split(" ", limit = 2)
                var flag1 = true
                try {
                    for (command in userRunnable.scriptCommands) {
                        if (command.getLabel() == args[0]) {
                            printStream.println(command.execute(if (args.size > 1) args[1] else " "))
                            flag1 = false
                            break
                        }
                    }
                    if (flag1) {
                        errorStream.println("Command not found or u can't use this command")
                    }
                } catch (e: RuntimeException) {
                    errorStream.println(e.message)
                }
            }
            fileReader.close()
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Can't find file \"$file\".")
        } catch (e: SecurityException) {
            throw IllegalArgumentException("Can't access file \"$file\".")
        } catch (e: IOException) {
            throw IllegalArgumentException("Error occurred accessing file \"$file\".")
        }
        printStream.append("Executed script from file \"").append(argument).append(".")
        return "Script read"
    }
}