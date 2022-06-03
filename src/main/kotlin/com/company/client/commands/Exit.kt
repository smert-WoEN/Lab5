package com.company.client.commands

class Exit: Command {

    override fun getLabel(): String {
        return "exit"
    }

    override fun getDescription(): String {
        return "Exit from client."
    }

    override fun execute(argument: String): String {
        //переделать выход
        System.exit(0)
        return "Exited."
    }
}