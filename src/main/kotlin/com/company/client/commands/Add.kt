package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import com.company.collection.LabWorkCreatorClient

class Add(private val socket: ClientSocket,
          private val labWorkCreatorClient: LabWorkCreatorClient): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "add"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Add new element to collection from console"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        socket.sendMessage(Message(getLabel(), labWorkCreatorClient.inputLabWorkFromConsole()))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")
    }
}