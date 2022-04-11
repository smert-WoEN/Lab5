package com.company.ui

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.commands.*
import java.lang.Runnable
import com.company.ui.CommandReader.UserCommand
import java.io.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.Scanner

/**
 * Main command execution runnable
 */
class UserRunnable(
    private val availableCommands: Array<Command>,
    private val printStream: PrintStream,
    scanner: Scanner,
    labWorkCollections: LabWorkCollections,
    labWorkCreator: LabWorkCreator,
    string: String
) : Runnable {
    private val commandReader: CommandReader

    /**
     * User runnable constructor
     *
     * @param availableCommands set of available commands
     * @param printStream       PrintStream to output to
     * @param inputStream       InputStream to input from
     */
    init {
        commandReader = CommandReader(scanner)
    }

    /**
     * Execute specified user command
     *
     * @param userCommand User command
     */
    fun Execute(userCommand: UserCommand) {
        var commandIsFound = false
        var response = "Command gave no response."
        try {
            for (command in availableCommands) {
                if (userCommand.Command == command.getLabel() && !commandIsFound) {
                    commandIsFound = true
                    response = command.execute(userCommand.Argument)
                }
            }
            if (!commandIsFound) response =
                "Unknown command \"" + userCommand.Command + "\". try \"help\" for list of commands"
        } catch (e: IllegalArgumentException) {
            response = e.message!!
        } catch (e: Exception) {
            response = "Unexpected error: " + e.message + ". This is a bug!"
            e.printStackTrace()
        }
        printStream.println(response)
    }

    /**
     * Thing that executes commands from bufferedReader until System.exit
     */
    override fun run() {
        while (true) {
            Execute(commandReader.readCommandFromBufferedReader())
        }
    }

    /**
     * All possible commands
     */
    val allCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        ExecuteScript(labWorkCollections,labWorkCreator,string, this),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    /**
     * Commands that user can use
     */
    val userCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        ExecuteScript(labWorkCollections,labWorkCreator,string, this),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    /**
     * Programs that execute_script can use
     */
    val scriptCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        //Execute_script(),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )


}