package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import org.apache.commons.codec.digest.DigestUtils
import java.awt.Shape
import java.util.ResourceBundle

class Register(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "register"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "register"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        return try {
            val args = argument.split(" ", limit = 3)
            if (args[1] == args[2]) {
                socket.sendMessage(
                    com.company.client.Register(
                        args[0], DigestUtils.sha256Hex(args[1]),
                        DigestUtils.sha256Hex(args[2])
                    )
                )
                val answer = socket.readMessage() as Message
                (if (answer.string == "answer") answer.any as String else "bad answer, idk add to collection.")
            } else {
                "errLP"
            }
        } catch (e: RuntimeException) {
            "incorrect"
        }
    }
}