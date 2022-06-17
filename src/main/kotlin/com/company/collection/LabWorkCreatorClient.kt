package com.company.collection

import com.company.client.ClientSocket
import com.company.superclasses.Difficulty
import java.io.PrintStream
import java.util.Scanner
import kotlin.properties.Delegates

class LabWorkCreatorClient(private val printStream: PrintStream, private val errorStream: PrintStream, private val scanner: Scanner) {

    fun inputLabWorkFromConsole(socket: ClientSocket): LabWorkClient {
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
        return LabWorkClient(name, Coordinates(x, y), minimalPoint, maximalPoint, difficulty,
            Discipline(nameDiscipline, lectureHours, practiceHours, selfStudyHours, labsCount), socket.token.login)
    }
}