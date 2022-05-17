package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket

class Info(private val socket: ClientSocket): Command {
    override fun getLabel(): String {
        return "info"
    }

    override fun getDescription(): String {
        return "dive information about collection"
    }

    override fun execute(argument: String): String {
        socket.sendMessage(Message(getLabel(), getLabel()))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")    }
}