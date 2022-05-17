package com.company.commands

import com.company.ui.UserRunnable
import java.util.*
@Deprecated("")
class Help(private val userRunnable: UserRunnable): Command {

    override fun getLabel(): String {
        return "help"
    }

    override fun getDescription(): String {
        return "Gives the list of available commands."
    }

    override fun execute(argument: String): String {
        var response = "Available commands:\n"
        Arrays.stream(userRunnable.allCommands).forEach { value ->  response += value.getLabel() + " " + value.getArgumentLabel() + " " + value.getDescription() + " \n"}
        response += "Collection class members have to be entered line-by-line. Standard types (including primitive types) have to be entered in the same line as the command."
        return response
    }
}