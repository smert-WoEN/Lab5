package com.company.ui

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.commands.*
import java.io.PrintStream
import java.lang.Runnable
import java.util.Scanner


/**
 * Main command execution runnable
 */
class UserRunnable(
    labWorkCollections: LabWorkCollections,
    labWorkCreator: LabWorkCreator,
    string: String,
    private val printStream: PrintStream,
    private val errorStream: PrintStream,
    private val scanner: Scanner
) : Runnable {

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
        ExecuteScript(this, printStream, errorStream),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
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
        ExecuteScript(this, printStream, errorStream),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
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
        //UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        //Execute_script(),
        //Exit(),
        //Add(labWorkCreator, labWorkCollections),
        //AddIfMin(labWorkCreator, labWorkCollections),
        //AddIfMax(labWorkCreator, labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    override fun run() {
        var flag = true
        while (flag) {
            printStream.print("Input command: ")
            val arg = scanner.nextLine()
            val args = arg.split(" ", limit = 2)
            var flag1 = true
            try {
                for (command in userCommands) {
                    if (args[0] == "exit") {
                        flag = false
                        flag1 = false
                        break
                    }
                    if (command.getLabel() == args[0]) {
                        printStream.println(command.execute(if (args.size > 1) args[1] else " "))
                        flag1 = false
                        break
                    }
                }
                if (flag1) {
                    errorStream.println("Command not found")
                }
            } catch (e: RuntimeException) {
                errorStream.println(e.message)
            }

        }
    }


}