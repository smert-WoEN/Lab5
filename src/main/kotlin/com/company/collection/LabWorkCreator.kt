package com.company.collection

import com.company.superclasses.Difficulty
import java.io.PrintStream
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.properties.Delegates

class LabWorkCreator(private val printStream: PrintStream, private val errorStream: PrintStream, private val scanner: Scanner, initialId: Int = 0) {

    private val id = AtomicInteger(initialId)

    fun getId() = id.get()

    private fun getIdNew() = id.getAndAdd(1)

    fun inputNewLabWorkFromConsole(): LabWork {
        return inputLabWorkFromConsole(getIdNew(), Date())
    }

    fun inputNewLabWorkFromCSVString(string: String): LabWork {
        return inputLabWorkFromCSVString(string, getIdNew(), Date())
    }

    fun inputLabWorkFromCSVString(string: String, id: Int, creationDate: Date): LabWork {
        val values = string.split(",", limit = 11)
        return ValidatorLabWork().checkLabWork(
            id,
            (values[0].trim()),
            ValidatorCoordinates().checkCoordinates(values[1].trim().toInt(), values[2].trim().toDouble()),
            creationDate,
            values[3].trim().toInt(),
            values[4].trim().toDouble(),
            Difficulty.valueOf(values[5].trim()),
            ValidatorDiscipline().checkDiscipline(
                values[6].trim(),
                if (values[7].trim() == "null") null else values[7].trim().toLong(),
                values[8].trim().toInt(),
                values[9].trim().toLong(),
                values[10].trim().toInt()
            )
        )
    }

    fun inputLabWorkFromConsole(id: Int, creationDate: Date): LabWork {
        val validatorLabWork = ValidatorLabWork()
        val validatorCoordinates = ValidatorCoordinates()
        val validatorDiscipline = ValidatorDiscipline()
        lateinit var name: String
        var flag = false
        do {
            try {
                printStream.print("Input name LabWork: ")
                val string = scanner.nextLine()
                name = validatorLabWork.checkName(string)
                flag = true
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var x by Delegates.notNull<Int>()
        do {
            try {
                printStream.print("Input Coordinates x: ")
                val string = (scanner.nextLine()).toInt()
                x = validatorCoordinates.checkX(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var y by Delegates.notNull<Double>()
        do {
            try {
                printStream.print("Input Coordinates y: ")
                val string = (scanner.nextLine()).toDouble()
                y = validatorCoordinates.checkY(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }

        } while (!flag)
        flag = false
        var minimalPoint by Delegates.notNull<Int>()
        do {
            try {
                printStream.print("Input minimalPoint: ")
                val string = (scanner.nextLine()).toInt()
                minimalPoint = validatorLabWork.checkMinimalPoint(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var maximalPoint by Delegates.notNull<Double>()
        do {
            try {
                printStream.print("Input maximalPoint: ")
                val string = (scanner.nextLine()).toDouble()
                maximalPoint = validatorLabWork.checkMaximalPoint(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var difficulty by Delegates.notNull<Difficulty>()
        do {
            try {
                printStream.print("Input difficulty " + Difficulty.values().contentToString() + " : ")
                difficulty = Difficulty.valueOf(scanner.nextLine())
                flag = true
            } catch (e: IllegalArgumentException) {
                errorStream.println("Not found.")
            }
        } while (!flag)
        flag = false
        lateinit var nameDiscipline: String
        do {
            try {
                printStream.print("Input name discipline: ")
                val string = scanner.nextLine()
                nameDiscipline = validatorDiscipline.checkName(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var lectureHours: Long? = null
        do {
            try {
                printStream.print("Input lectureHours: ")
                val string = scanner.nextLine().toLongOrNull()
                lectureHours = validatorDiscipline.checkLectureHours(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var practiceHours by Delegates.notNull<Int>()
        do {
            try {
                printStream.print("Input practiceHours: ")
                val string = scanner.nextLine().toInt()
                practiceHours = validatorDiscipline.checkPracticeHours(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var selfStudyHours by Delegates.notNull<Long>()
        do {
            try {
                printStream.print("Input selfStudyHours: ")
                val string = scanner.nextLine().toLong()
                selfStudyHours = validatorDiscipline.checkSelfStudyHours(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        flag = false
        var labsCount by Delegates.notNull<Int>()
        do {
            try {
                printStream.print("Input labsCount: ")
                val string = scanner.nextLine().toInt()
                labsCount = validatorDiscipline.checkLabsCount(string)
                flag = true
            } catch (e: NumberFormatException) {
                errorStream.println("It's not number!")
            } catch (e: IllegalArgumentException) {
                errorStream.println(e.message)
            }
        } while (!flag)
        return LabWork(id, name, Coordinates(x, y), creationDate, minimalPoint, maximalPoint, difficulty,
        Discipline(nameDiscipline, lectureHours, practiceHours, selfStudyHours, labsCount))
    }
}