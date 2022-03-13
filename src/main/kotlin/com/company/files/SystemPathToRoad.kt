package com.company.files

import com.company.superclasses.PathUnzip

class SystemPathToRoad (private val pathToRoad: String): PathUnzip{
    override val path: String? = System.getenv(pathToRoad)
        get() {
            if (field.isNullOrBlank())
                throw IllegalArgumentException("Can't find path \"$pathToRoad\".")
            else {
                return field
            }
        }
}