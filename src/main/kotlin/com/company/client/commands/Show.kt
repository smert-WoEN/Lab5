package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket

class Show(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "show"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "show elements"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        socket.sendMessage(Message(getLabel(), getLabel()))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")    }
}