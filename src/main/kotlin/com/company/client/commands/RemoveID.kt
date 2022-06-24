package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import kotlin.properties.Delegates

class RemoveID(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "remove"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "remove element from server collection by id"
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
            return "noNumber"
        }
        socket.sendMessage(Message(getLabel(), "$id ${socket.token.login}", socket.token.token))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer" && answer.token == socket.token.token) answer.any as String else "bad answer, idk add to collection.")
    }

}