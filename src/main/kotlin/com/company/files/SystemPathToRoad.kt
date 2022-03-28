package com.company.files

import com.company.superclasses.PathUnzip

class SystemPathToRoad (private val pathToRoad: String): PathUnzip{
    override val path: String
        get() = System.getenv("wfejof")
                ?: throw IllegalArgumentException("Can't find path \"$pathToRoad\". Please create this path.")
}