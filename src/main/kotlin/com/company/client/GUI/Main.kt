package com.company.client.GUI

import com.company.Message
import com.company.client.ClientSocket
import com.company.client.commands.Add
import com.company.client.commands.RemoveID
import com.company.collection.*
import com.company.superclasses.Difficulty
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.swing.*
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel
import javax.swing.table.TableRowSorter
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class Main(val socket: ClientSocket, var resourceBundle: ResourceBundle, val frame: JFrame) {
    val lock: ReadWriteLock = ReentrantReadWriteLock()
    var labWorkCollection = ArrayList<LabWork>()

    val runBoolean = AtomicBoolean(true)
    lateinit var localeLang: Locale
    lateinit var jButton: JButton
    lateinit var languageLabel: JLabel
    lateinit var addButton: JButton
    lateinit var removeButton: JButton
    lateinit var userLabel: JLabel
    lateinit var jTable: JTable
    lateinit var pane: JScrollPane
    init {
        lock.writeLock().lock()
        try {
            socket.sendMessage(com.company.Collection(HashSet(), socket.token.token))
            val message = socket.readMessage()
            if (message is com.company.Collection) {
                if (message.token == socket.token.token) {
                    labWorkCollection = ArrayList<LabWork>(message.hashSet)
                }
            }
        } finally {
            lock.writeLock().unlock()
        }
        val columns = arrayOf("id",
            resourceBundle.getString("name"),"x","y",resourceBundle.getString("creationDate"),
            resourceBundle.getString("minimalPoint"),resourceBundle.getString("maximalPoint"),
            resourceBundle.getString("difficulty"), resourceBundle.getString("disciplineName"),
            resourceBundle.getString("lectureHours"),resourceBundle.getString("practiceHours"),
            resourceBundle.getString("selfStudyHours"),resourceBundle.getString("labsCount"),
            resourceBundle.getString("owner"))
        val model = DefaultTableModel(columns, 0)
        lock.writeLock().lock()
        try {
            labWorkCollection.forEach { model.addRow(arrayOf(it.id, it.name, it.coordinates.x, it.coordinates.y,
                it.creationDate, it.minimalPoint, it.maximalPoint, it.difficulty, it.discipline.name, it.discipline.lectureHours,
                it.discipline.practiceHours, it.discipline.selfStudyHours, it.discipline.labsCount, it.owner)) }
        } finally {
            lock.writeLock().unlock()
        }
        println(model.rowCount)
        val table = JTable(model)
        val sorter = TableRowSorter<TableModel>(table.model)
        table.rowSorter = sorter
        val sortKeys = ArrayList<RowSorter.SortKey>(25)
        sortKeys.add(RowSorter.SortKey(0, SortOrder.ASCENDING))
        sorter.sortKeys = sortKeys
        pane = JScrollPane(table)
        val mainPanel = JPanel(BorderLayout())
        thread {
            while (runBoolean.get()) {
                Thread.sleep(10000)
                lock.writeLock().lock()
                try {
                    socket.sendMessage(com.company.Collection(HashSet(), socket.token.token))
                    val message = socket.readMessage()
                    if (message is com.company.Collection) {
                        if (message.token == socket.token.token) {
                            labWorkCollection = ArrayList<LabWork>(message.hashSet)
                        }
                    }
                } finally {
                    lock.writeLock().unlock()
                }
                val columns = arrayOf("id",
                    resourceBundle.getString("name"),"x","y",resourceBundle.getString("creationDate"),
                    resourceBundle.getString("minimalPoint"),resourceBundle.getString("maximalPoint"),
                    resourceBundle.getString("difficulty"), resourceBundle.getString("disciplineName"),
                    resourceBundle.getString("lectureHours"),resourceBundle.getString("practiceHours"),
                    resourceBundle.getString("selfStudyHours"),resourceBundle.getString("labsCount"),
                    resourceBundle.getString("owner"))
                val model = DefaultTableModel(columns, 0)
                lock.writeLock().lock()
                try {
                    labWorkCollection.forEach { model.addRow(arrayOf(it.id, it.name, it.coordinates.x, it.coordinates.y,
                        it.creationDate, it.minimalPoint, it.maximalPoint, it.difficulty, it.discipline.name, it.discipline.lectureHours,
                        it.discipline.practiceHours, it.discipline.selfStudyHours, it.discipline.labsCount, it.owner)) }
                } finally {
                    lock.writeLock().unlock()
                }
                println(model.rowCount)
                val table = JTable(model)
                val sorter = TableRowSorter<TableModel>(table.model)
                table.rowSorter = sorter
                val sortKeys = ArrayList<RowSorter.SortKey>(25)
                sortKeys.add(RowSorter.SortKey(0, SortOrder.ASCENDING))
                sorter.sortKeys = sortKeys
                mainPanel.remove(pane)
                pane = JScrollPane(table)
                mainPanel.add(BorderLayout.CENTER, pane)
                pane.revalidate()
                pane.repaint()
                pane.isVisible = true
                frame.isVisible = true
            }
        }
        jButton = JButton(resourceBundle.getString("main"))
        languageLabel = JLabel("Language")
        addButton = JButton(resourceBundle.getString("add"))
        addButton.addActionListener {
            val validatorLabWork = ValidatorLabWork()
            val validatorCoordinates = ValidatorCoordinates()
            val validatorDiscipline = ValidatorDiscipline()
            lateinit var name: String
            var flag = false
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputName"))
                    name = validatorLabWork.checkName(string)
                    flag = true
                } catch (e: IllegalArgumentException){
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("notName"))
                }
            } while (!flag)
            flag = false
            var x by Delegates.notNull<Int>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputX")).toInt()
                    x = validatorCoordinates.checkX(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errX"))
                }
            } while (!flag)
            flag = false
            var y by Delegates.notNull<Double>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputY")).toDouble()
                    y = validatorCoordinates.checkY(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errY"))
                }

            } while (!flag)
            flag = false
            var minimalPoint by Delegates.notNull<Int>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputMinimalPoint")).toInt()
                    minimalPoint = validatorLabWork.checkMinimalPoint(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errMP"))
                }
            } while (!flag)
            flag = false
            var maximalPoint by Delegates.notNull<Double>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputMaximalPoint")).toDouble()
                    maximalPoint = validatorLabWork.checkMaximalPoint(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errMAP"))
                }
            } while (!flag)
            flag = false
            var difficulty by Delegates.notNull<Difficulty>()
            do {
                try {
                    difficulty = Difficulty.valueOf(JOptionPane.showInputDialog(frame, resourceBundle.getString("inputDifficulty")))
                    flag = true
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errDifficulty"))
                }
            } while (!flag)
            flag = false
            lateinit var nameDiscipline: String
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputDiscipline"))
                    nameDiscipline = validatorDiscipline.checkName(string)
                    flag = true
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("notName"))
                }
            } while (!flag)
            flag = false
            var lectureHours: Long? = null
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputLH")).toLongOrNull()
                    lectureHours = validatorDiscipline.checkLectureHours(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errLH"))
                }
            } while (!flag)
            flag = false
            var practiceHours by Delegates.notNull<Int>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputPH")).toInt()
                    practiceHours = validatorDiscipline.checkPracticeHours(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errPH"))
                }
            } while (!flag)
            flag = false
            var selfStudyHours by Delegates.notNull<Long>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputSSH")).toLong()
                    selfStudyHours = validatorDiscipline.checkSelfStudyHours(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errSSH"))
                }
            } while (!flag)
            flag = false
            var labsCount by Delegates.notNull<Int>()
            do {
                try {
                    val string = JOptionPane.showInputDialog(frame, resourceBundle.getString("inputCount")).toInt()
                    labsCount = validatorDiscipline.checkLabsCount(string)
                    flag = true
                } catch (e: NumberFormatException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("noNumber"))
                } catch (e: IllegalArgumentException) {
                    JOptionPane.showMessageDialog(frame, resourceBundle.getString("errIC"))
                }
            } while (!flag)
            val labWorkClient = LabWorkClient(name, Coordinates(x, y), minimalPoint, maximalPoint, difficulty,
            Discipline(nameDiscipline, lectureHours, practiceHours, selfStudyHours, labsCount), socket.token.login)
            lock.writeLock().lock()
            val answer = try {
                socket.sendMessage(Message("add", labWorkClient, socket.token.token))
                socket.readMessage() as Message
            } finally {
                lock.writeLock().unlock()
            }
            if (answer.string == "answer" && answer.token == socket.token.token) {
                JOptionPane.showMessageDialog(frame, resourceBundle.getString("addS"))
            }
        }
        removeButton = JButton(resourceBundle.getString("remove"))
        removeButton.addActionListener {
            val id = JOptionPane.showInputDialog(frame, resourceBundle.getString("askRemove"))
            val remove = RemoveID(socket)
            val message = remove.execute(id)
            JOptionPane.showMessageDialog(frame, resourceBundle.getString(message))
        }
        userLabel = JLabel(resourceBundle.getString("user"))
        localeLang = Locale("ru", "RU")
        val languageBox = JComboBox<String>()
        languageBox.maximumSize = Dimension(50, 40)
        languageBox.isEditable = false
        languageBox.addItem("Русский")
        languageBox.addItem("Suomalainen")
        languageBox.addItemListener { e ->
            if (e.item.toString() == "Русский") {
                localeLang = Locale("ru", "RU")
            } else if (e.item.toString() == "Suomalainen") {
                localeLang = Locale("fi", "FI")
            }
//            } else if (e.item.toString() == "Dansk") {
//                locale = Locale("dan", "DAN")
//            } else if (e.item.toString() == "Deutsch") {
//                locale = Locale("de", "DE")
//            }
            resourceBundle = ResourceBundle.getBundle("com.company.client.lang.Bundle", localeLang)
            changeLang()
        }


        val userPanel = JPanel()
        userPanel.add(userLabel)
        userPanel.add(JLabel(socket.token.login))
        val commandPanel = JPanel(GridLayout(0, 1))
        commandPanel.add(addButton)
        commandPanel.add(removeButton)
        val languagePanel = JPanel()
        languagePanel.add(languageLabel)
        languagePanel.add(languageBox)
        mainPanel.add(BorderLayout.CENTER, pane)
        mainPanel.add(BorderLayout.NORTH, userPanel)
        mainPanel.add(BorderLayout.WEST, commandPanel)
        mainPanel.add(BorderLayout.SOUTH, languagePanel)
        frame.add(mainPanel)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(960, 720)
        frame.isVisible = true
    }

    private fun changeLang() {
        jButton.text = resourceBundle.getString("main")
        addButton.text = resourceBundle.getString("add")
        removeButton.text = resourceBundle.getString("remove")
        userLabel.text = resourceBundle.getString("user")
    }
}