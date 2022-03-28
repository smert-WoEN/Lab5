package com.company.files

class SystemPathToRoad (private val pathToRoad: String) {
    val path: String
        get() = System.getenv(pathToRoad)
                ?: throw IllegalArgumentException("Can't find path \"$pathToRoad\". Please create this path.")
}