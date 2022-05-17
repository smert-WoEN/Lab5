package com.company.client.ui

import com.company.client.ClientSocket
import com.company.client.commands.*
import com.company.collection.LabWorkCreatorClient
import java.io.PrintStream
import java.util.Scanner
import java.util.concurrent.atomic.AtomicBoolean

class ClientRunnable(
    socket: ClientSocket,
    labWorkCreatorClient: LabWorkCreatorClient,
    private val printStream: PrintStream,
    private val errorStream: PrintStream,
    private val scanner: Scanner): Runnable {

    val allCommands = arrayOf(
        Add(socket, labWorkCreatorClient),
        AddIfMax(socket, labWorkCreatorClient),
        AddIfMin(socket, labWorkCreatorClient),
        Clear(socket),
        FilterMaxPoint(socket),
        GroupMinPoint(socket),
        Help(this),
        Info(socket),
        RemoveID(socket),
        RemoveLower(socket),
        Show(socket),
        SumMaxPoint(socket),
        UpdateID(socket, labWorkCreatorClient),
        ExecuteScript(this, printStream, errorStream)
    )

    val userCommands = arrayOf(
        Add(socket, labWorkCreatorClient),
        AddIfMax(socket, labWorkCreatorClient),
        AddIfMin(socket, labWorkCreatorClient),
        Clear(socket),
        FilterMaxPoint(socket),
        GroupMinPoint(socket),
        Help(this),
        Info(socket),
        RemoveID(socket),
        RemoveLower(socket),
        Show(socket),
        SumMaxPoint(socket),
        UpdateID(socket, labWorkCreatorClient),
        ExecuteScript(this, printStream, errorStream)
    )

    val scriptCommands = arrayOf(
        Help(this),
        Info(socket),
        Show(socket),
//        UpdateID(socket, labWorkCreatorClient),

        //Add(socket, labWorkCreatorClient),
        //AddIfMax(socket, labWorkCreatorClient),
        //AddIfMin(socket, labWorkCreatorClient),
        Clear(socket),
        FilterMaxPoint(socket),
        GroupMinPoint(socket),
        RemoveID(socket),
        RemoveLower(socket),
        SumMaxPoint(socket)
    )


    override fun run() {
        val flag = AtomicBoolean(true)
        while (flag.get()) {
            printStream.print("Input command: ")
            val arg = scanner.nextLine()
            val args = arg.split(" ", limit = 2)
            val flag1 = AtomicBoolean(true)
            try {
                for (command in userCommands) {
                    if (args[0] == "exit") {
                        flag.set(false)
                        flag1.set(false)
                        break
                    }
                    if (command.getLabel() == args[0]) {
                        printStream.println(command.execute(if (args.size > 1) args[1] else " "))
                        flag1.set(false)
                        break
                    }
                }
                if (flag1.get()) {
                    errorStream.println("Command not found")
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
                //errorStream.println(e.message)
            }
        }
    }
}