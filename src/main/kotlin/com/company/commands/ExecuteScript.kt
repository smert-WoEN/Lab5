package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.ui.CommandReader
import com.company.ui.UserRunnable
import java.io.*
import java.nio.charset.Charset
import java.util.*

class ExecuteScript(private val labWorkCollections: LabWorkCollections,
private val labWorkCreator: LabWorkCreator,
private val string: String,
private val userRunnable: UserRunnable): Command {
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
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
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
            val userRunnable = UserRunnable(this.userRunnable.scriptCommands, printStream, fileReader, labWorkCollections, labWorkCreator, string)
            val stringBuilder = StringBuilder()
            while (fileReader.available() > 0) stringBuilder.append(fileReader.read().toChar())
            Arrays.stream(stringBuilder.toString().split("[\\r\\n]+".toRegex()).toTypedArray())
                .forEach { line: String ->
                    if (!line.isBlank()) printStream.append(line).append("\n")
                    val formattedLine = line
                        .replace("\\breplace_if_greater\\b".toRegex(), "replace_if_greater_csv")
                        .replace("\\bupdate\\b".toRegex(), "update_csv")
                        .replace("\\binsert\\b".toRegex(), "insert_csv")
                    userRunnable.Execute(CommandReader.readCommandFromString(formattedLine))
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
        return "outputStream.toString(Charset.defaultCharset())"
    }
}