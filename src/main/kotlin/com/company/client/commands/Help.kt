package com.company.client.commands

import com.company.client.ui.ClientRunnable
import java.util.*

class Help(private val clientRunnable: ClientRunnable): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "help"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Gives the list of available commands."
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        var response = "Available commands:\n"
        Arrays.stream(clientRunnable.allCommands).forEach { value ->  response += value.getLabel() + " " + value.getArgumentLabel() + " " + value.getDescription() + " \n"}
        response += "Collection class members have to be entered line-by-line. Standard types (including primitive types) have to be entered in the same line as the command."
        return response    }
}