package com.company.client.commands

interface Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    fun getLabel(): String

    /**
     * Command argument format
     *
     * @return argument format
     */
    fun getArgumentLabel(): String {
        return ""
    }

    /**
     * Command description
     *
     * @return description
     */
    fun getDescription(): String

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    fun execute(argument: String = ""): String
}