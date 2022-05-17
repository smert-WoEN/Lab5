package com.company.commands
@Deprecated("")
class Exit: Command {

    override fun getLabel(): String {
        return "exit"
    }

    override fun getDescription(): String {
        return "Exit the program (without saving)."
    }

    override fun execute(argument: String): String {
        //переделать выход
        System.exit(0)
        return "Exited."
    }
}