package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import kotlin.properties.Delegates

class RemoveLower(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "removeLower"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "removeLover element from collection by minValue"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        var minPoint by Delegates.notNull<Int>()
        try {
            minPoint = argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        socket.sendMessage(Message(getLabel(), "$minPoint ${socket.token.login}", socket.token.token))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer" && socket.token.token == answer.token) answer.any as String else "bad answer, idk add to collection.")    }
}