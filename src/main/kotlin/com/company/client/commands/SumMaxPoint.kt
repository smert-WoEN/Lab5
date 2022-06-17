package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket

class SumMaxPoint(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "sumMaxPoint"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "sum maxPoint element from collection"
    }


    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        socket.sendMessage(Message(getLabel(), getLabel(), socket.token.token))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer" && answer.token == socket.token.token) answer.any as String else "bad answer, idk add to collection.")
    }

}