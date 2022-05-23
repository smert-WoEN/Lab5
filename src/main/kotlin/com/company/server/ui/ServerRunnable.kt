package com.company.server.ui

import com.company.Message
import com.company.SocketCloseException
import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.server.SomeClient
import com.company.server.commands.*
import com.company.server.serverCommands.Help
import com.company.server.serverCommands.Save
import org.apache.logging.log4j.Logger
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
labWorkCollections: LabWorkCollections,
labWorkCreator: LabWorkCreator,
string: String,
private val printStream: PrintStream,
private val errorStream: PrintStream,
private val scanner: Scanner,
private val logger: Logger)
    : Runnable {

    val serverCommands = arrayOf(
        Save(labWorkCollections, labWorkCreator, string),
        Help(this),
        com.company.server.serverCommands.Info(labWorkCollections)
    )

    val commands = arrayOf(
        Add(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        Info(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        Show(labWorkCollections),
        SumMaxPoint(labWorkCollections),
        UpdateID(labWorkCollections),
        UpdateID1(labWorkCreator, labWorkCollections)
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
                val arg = scanner.nextLine()
                val args = arg.split(" ", limit = 2)
                var flag = true
                for (command in serverCommands) {
                    if (args[0] == "exit") {
                        flag = false
                        atomicBoolean.set(false)
                        for (command1 in serverCommands) {
                            if (command1.getLabel() == "save") {
                                printStream.println(command.execute(if (args.size > 1) args[1] else " "))
                                break
                            }
                        }
                        printStream.println("finish program")
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
                            if (message is Message) {
                                var flag = true
                                for (command in commands) {
                                    if (command.getLabel() == message.string) {
                                        someClient.sendMess(Message("answer", command.execute(message.any)))
                                        flag = false
                                        break
                                    }
                                }
                                if (flag) {
                                    someClient.sendMess(Message("answer", "Command not found"))
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