package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import kotlin.properties.Delegates

class FilterMaxPoint(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "filterMaxPoint"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "filter by max Point"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        var maxPoint by Delegates.notNull<Double>()
        try {
            maxPoint = argument.toDouble()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("It's not number!")
        }
        socket.sendMessage(Message(getLabel(), maxPoint))
        val answer = socket.readMessage() as Message
        return (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")
    }
}