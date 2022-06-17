package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import com.company.collection.LabWorkCreatorClient

class AddIfMax(private val socket: ClientSocket,
private val labWorkCreatorClient: LabWorkCreatorClient): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "addIfMax"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Add new element to server collection from console if max more then maxElement"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        socket.sendMessage(Message(getLabel(), labWorkCreatorClient.inputLabWorkFromConsole(socket), socket.token.token))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer" && answer.token == socket.token.token) answer.any as String else "bad answer, idk add to collection.")
    }
}