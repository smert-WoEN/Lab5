package com.company

import com.company.classes.Discipline


fun main() {
    val discipline = Discipline()
    print("input name discipline: ")
    discipline.name = readLine().toString()
    println("hu")
    println(discipline.name)


}