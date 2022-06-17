package com.company.collection


import com.company.superclasses.Difficulty
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.*
import kotlin.collections.HashSet

class LabWorkBD(private val port: Int, private val login: String, private val password: String) {
    private val connection: Connection = DriverManager.getConnection("jdbc:postgresql://localHost:$port/studs",
    login, password) //PSQLException
    private val stml = connection.createStatement()

    fun initialize() {

    }

    private fun getElementById(id: Int): LabWork { //??????
        val sql = "SELECT * FROM labwork where id=$id"
        val result = stml.executeQuery(sql)
        result.next()
        return unZipValues(result)
    }

    fun addElement(labWork: LabWorkClient) {
        println(labWork.difficulty.label)
        val sql = "INSERT INTO labwork ($mainFields, \"owner\")" +
                "VALUES (${zipValues(labWork)}, '${labWork.owner}')"
        stml.executeUpdate(sql)
    }

    fun getLast(): LabWork {
        val sql = "select * from labwork order by id desc limit 1"
        val result = stml.executeQuery(sql)
        result.next()
        return (unZipValues(result))
    }

    fun removeById(id: Int, owner: String){
        val sql = "DELETE from labwork where id='$id' and owner='$owner'"
        stml.executeUpdate(sql)
    }

    fun removeByOwner(owner: String) {
        val sql = "DELETE from labwork where owner='$owner'"
        stml.executeUpdate(sql)
    }

    fun removeLessMinPoint(minPoint: Int, owner: String) {
        val sql = "DELETE from labwork where owner='$owner' and minimalPoint < '$minPoint'"
        stml.executeUpdate(sql)
    }

    fun updateById(id: Int, labWork: LabWorkClient): LabWork {
        val sql = "UPDATE labwork set \"name\"='${labWork.name}'," +
                "x='${labWork.coordinates.x}'," +
                "y='${labWork.coordinates.y}'," +
                "\"minimalPoint\"='${labWork.minimalPoint}'," +
                "\"maximalPoint\"='${labWork.maximalPoint}'," +
                "\"difficulty\"='${labWork.difficulty.label}'," +
                "\"disciplineName\"='${labWork.discipline.name}'," +
                "\"lectureHours\"='${labWork.discipline.lectureHours}'," +
                "\"practiceHours\"='${labWork.discipline.practiceHours}'," +
                "\"selfStudyHours\"='${labWork.discipline.selfStudyHours}'," +
                "\"labsCount\"='${labWork.discipline.labsCount}'" +
                "where id='$id' and \"owner\"='${labWork.owner}'"
        stml.executeUpdate(sql)
        return getElementById(id)
    }

    fun getLabWorks(): HashSet<LabWork> {
        val sql = "Select * from labwork"
        val result = stml.executeQuery(sql)
        val collection = HashSet<LabWork>()
        while (result.next()) {
            collection.add(unZipValues(result))
        }
        return collection
    }

    fun getUserPassword(login: String): String {
        val sql = "SELECT * from users where \"login\"='$login'"
        val result = stml.executeQuery(sql)
        result.next()
        return result.getString("password")
    }

    fun addUser(login: String, password: String) {
        val sql = "INSERT INTO users (\"login\", \"password\")" +
                "VALUES ('$login', '$password')"
        stml.executeUpdate(sql)
    }

    private fun zipValues(labWork: LabWorkClient): String = "'${labWork.name}', ${labWork.coordinates.x}, ${labWork.coordinates.y}," +
    "${labWork.minimalPoint}, ${labWork.maximalPoint}, '${labWork.difficulty.label}'," +
    "'${labWork.discipline.name}', ${labWork.discipline.lectureHours}, ${labWork.discipline.practiceHours}," +
    "${labWork.discipline.selfStudyHours}, ${labWork.discipline.labsCount}"

    private val mainFields = "\"name\", x, y, \"minimalPoint\", \"maximalPoint\", \"difficulty\", \"disciplineName\", \"lectureHours\", " +
    "\"practiceHours\", \"selfStudyHours\", \"labsCount\""

    private fun unZipValues(result: ResultSet): LabWork = LabWork(
        result.getInt("id"),
        result.getString("name"),
        Coordinates(result.getInt("x"), result.getDouble("y")),
        Date(result.getTimestamp("creationDate").time),
        result.getInt("minimalPoint"),
        result.getDouble("maximalPoint"),
        Difficulty.valueOf(result.getString("difficulty")),
        Discipline(
            result.getString("disciplineName"),
            result.getLong("lectureHours"),
            result.getInt("practiceHours"),
            result.getLong("selfStudyHours"),
            result.getInt("labsCount")
        ),
        result.getString("owner")
    )
}