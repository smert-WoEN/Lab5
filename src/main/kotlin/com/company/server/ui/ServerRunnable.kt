package com.company.server.ui

import com.company.Message
import com.company.SocketCloseException
import com.company.client.Login
import com.company.client.Register
import com.company.collection.LabWorkBD
import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator
import com.company.collection.LabWorkCreator
import com.company.server.SomeClient
import com.company.server.Token
import com.company.server.commands.*
import com.company.server.serverCommands.Help
import org.apache.logging.log4j.Logger
import org.postgresql.util.PSQLException
import java.io.IOException
import java.io.PrintStream
import java.net.InetSocketAddress
import java.nio.channels.SelectionKey
import java.nio.channels.ServerSocketChannel
import java.nio.channels.spi.AbstractSelector
import java.nio.channels.spi.SelectorProvider
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ServerRunnable(private val port: Int,
private val labWorkComparator: LabWorkComparator,
                    private val labWorkBD: LabWorkBD,
private val printStream: PrintStream,
private val errorStream: PrintStream,
private val scanner: Scanner,
private val logger: Logger)
    : Runnable {

    val serverCommands = arrayOf(
        //Save(labWorkCollections, labWorkCreator, string),
        Help(this),
        //com.company.server.serverCommands.Info(labWorkCollections)
    )

    val commands = arrayOf(
        //Help(this),
        Add(labWorkComparator),
        AddIfMax(labWorkComparator),
        AddIfMin(labWorkComparator),
        Clear(labWorkComparator),
        FilterMaxPoint(labWorkComparator),
        GroupMinPoint(labWorkComparator),
        Info(labWorkComparator),
        RemoveID(labWorkComparator),
        RemoveLower(labWorkComparator),
        Show(labWorkComparator),
        SumMaxPoint(labWorkComparator),
        UpdateID(labWorkComparator),
        UpdateID1(labWorkComparator),
    )

    private val ip = "0.0.0.0"
    private val acceptor = ServerSocketChannel.open()
    private lateinit var selector: AbstractSelector
    private lateinit var acceptKey: SelectionKey
    @Throws(IOException::class)
    fun initialize() {
        acceptor.configureBlocking(false)
        val inet = InetSocketAddress(ip, port)
        acceptor.socket().bind(inet)
        acceptor.socket().reuseAddress = true
        selector = SelectorProvider.provider().openSelector()
        acceptKey = acceptor.register(selector, SelectionKey.OP_ACCEPT)
        acceptKey.interestOps(SelectionKey.OP_ACCEPT)
    }
    private val atomicBoolean = AtomicBoolean(true)
    private val runnable = Runnable {

        while (atomicBoolean.get()) {
            try {
                printStream.print("Input command: ")
                val arg = scanner.nextLine()
                val args = arg.split(" ", limit = 2)
                var flag = true
                for (command in serverCommands) {
                    if (args[0] == "exit") {
                        flag = false
                        atomicBoolean.set(false)
                        printStream.println("finish program")
                        logger.info("Finish program")
                        break
                    }
                    if (command.getLabel() == args[0]) {
                        printStream.println(command.execute(if (args.size > 1) args[1] else " "))
                        flag = false
                        break
                    }
                }
                if (flag) {
                    errorStream.println("Command not found")
                }
            } catch (e: RuntimeException) {
                logger.error(e)
                errorStream.println(e.message)
            }
        }

    }

    private val runnableThread = Thread(runnable)

    override fun run() {
        runnableThread.start()
        while (atomicBoolean.get()) {
            try {
                selector.select(500)
                val iterator = selector.selectedKeys().iterator()// as MutableIterator<SelectionKey>
                while (iterator.hasNext()) {
                    val key = iterator.next()
                    iterator.remove()
                    if (!key.isValid) {
                        continue
                    }
                    val someClient: SomeClient? = key.attachment() as? SomeClient
                    try {
                        if (key.isAcceptable) {
                            accept(key)
                        }
                        if (key.isReadable) {
                            val message = someClient!!.receiveMessage()
                            if (message is Login) {
                                println("res")
                                try {
                                    if (labWorkBD.getUserPassword(message.login) == message.password) {
                                        someClient.token = Token(message.login, message.password, message.timeDate)
                                        someClient.sendMess(Message("answer", "Login Ok", ""))
                                    } else {
                                        someClient.sendMess(Message("answer", "Password or Login Error",""))
                                    }
                                } catch (e: PSQLException) {
                                    someClient.sendMess(Message("answer", "Password or Login Error or register", ""))
                                }

                            }
                            if (message is Register) {
                                try {
                                    if (message.password == message.password2) {
                                        labWorkBD.addUser(message.login, message.password)
                                        someClient.sendMess(Message("answer", "RegisterComplete", ""))
                                    }
                                } catch (e: PSQLException) {
                                    someClient.sendMess(Message("answer", "login use, change login", ""))
                                }
                            }
                            if (message is Message) {
                                var flag = true
                                for (command in commands) {
                                    if (command.getLabel() == message.string && someClient.token.token == message.token) {
                                        someClient.sendMess(Message("answer", command.execute(message.any), someClient.token.token))
                                        flag = false
                                        break
                                    }
                                }
                                if (flag) {
                                    someClient.sendMess(Message("answer", "Command not found", someClient.token.token))
                                }
                            }

                        }
                        if (key.isWritable) {
                            //println("yes")
                            someClient!!.sendMessage()
                        }
                    } catch (e: SocketCloseException) {
                        logger.info(e.message)
                        //e.printStackTrace()
                        someClient?.disconnect()
                    } catch (e: IOException) {
                        logger.error(e)
                        //e.printStackTrace()
                        someClient?.disconnect()
                    }
                }
            } catch (e: IOException) {
                logger.error(e)
                //e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    fun accept(key: SelectionKey) {
        val channel = key.channel() as ServerSocketChannel
        val socket = channel.accept()
        val ipAddress = socket.socket().inetAddress.hostAddress
        //println(ipAddress)
        logger.info("connected from $ipAddress")
        socket.configureBlocking(false)
        val k = socket.register(key.selector(), SelectionKey.OP_READ or SelectionKey.OP_WRITE)
        //k.interestOps(SelectionKey.OP_READ)
        k.attach(SomeClient(logger, ipAddress, socket, k))
        //println("connection add")
    }
}