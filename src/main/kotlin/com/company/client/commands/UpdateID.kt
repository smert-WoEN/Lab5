package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import com.company.collection.LabWorkCreatorClient
import kotlin.properties.Delegates

class UpdateID(private val socket: ClientSocket,
                private val labWorkCreatorClient: LabWorkCreatorClient
): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "update"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "Update element to collection from console by id"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        var id by Delegates.notNull<Int>()
        try {
            id = argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        socket.sendMessage(Message(getLabel(), id))
        val answer = socket.readMessage() as Message
        if (answer.string == "answer") {
            if (answer.any as String != "yes") {
                return answer.any
            }
        }
        //return (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")
        socket.sendMessage(Message(getLabel()+"1", Message("$id", labWorkCreatorClient.inputLabWorkFromConsole())))
        val answer1 = socket.readMessage() as Message
        return (if (answer1.string == "answer") answer1.any as String else "bad answer, idk add to collection.")
    }
}