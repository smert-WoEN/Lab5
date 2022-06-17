package com.company.client.commands

import com.company.Message
import com.company.client.ClientSocket
import com.company.server.Token
import org.apache.commons.codec.digest.DigestUtils
import java.util.Date

class Login(private val socket: ClientSocket): Command {
    /**
     * Command label
     *
     * @return string that user has to input to use the command
     */
    override fun getLabel(): String {
        return "login"
    }

    /**
     * Command description
     *
     * @return description
     */
    override fun getDescription(): String {
        return "login"
    }

    /**
     * Execute the command
     *
     * @param argument command arguments
     * @return Command execution result
     */
    override fun execute(argument: String): String {
        return try {
            val args = argument.split(" ", limit = 2)
            val time = Date().time
            socket.sendMessage(com.company.client.Login(
                        args[0], DigestUtils.sha256Hex(args[1]), time))
                val answer = socket.readMessage() as Message
                (if (answer.string == "answer") {
                    if (answer.any as String == "Login Ok") {
                        socket.token = Token(args[0], DigestUtils.sha256Hex(args[1]), time)
                        socket.initToken = true
                        answer.any
                    } else {
                        answer.any
                    }
                } else "bad answer, idk add to collection.")
            } catch (e: RuntimeException) {
            "incorrect"
        }
    }
}