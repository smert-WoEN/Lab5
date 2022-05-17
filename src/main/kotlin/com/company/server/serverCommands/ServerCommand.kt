package com.company.server.serverCommands

interface ServerCommand {

    fun getLabel(): String

    fun getArgumentLabel(): String {
        return ""
    }

    fun getDescription(): String

    fun execute(argument: String = ""): String
}