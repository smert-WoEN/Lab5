package com.company.server.serverCommands

import com.company.server.ui.ServerRunnable
import java.util.*

class Help(private val serverRunnable: ServerRunnable): ServerCommand {
    override fun getLabel(): String {
        return "help"
    }

    override fun getDescription(): String {
        return "Gives the list of available commands."
    }

    override fun execute(argument: String): String {
        var response = "Available commands:\n"
        Arrays.stream(serverRunnable.serverCommands).forEach { value ->  response += value.getLabel() + " " + value.getArgumentLabel() + " " + value.getDescription() + " \n"}
        response += "Collection class members have to be entered line-by-line. Standard types (including primitive types) have to be entered in the same line as the command."
        return response
    }
}