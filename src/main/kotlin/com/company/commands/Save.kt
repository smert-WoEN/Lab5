package com.company.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.files.HashSetToList
import com.company.files.ListToFile
@Deprecated("")
class Save(private val labWorkCollections: LabWorkCollections, private val labWorkCreator: LabWorkCreator, private val string: String): Command {

    override fun getLabel(): String {
        return "save"
    }

    override fun getDescription(): String {
        return "save the collection to file"
    }

    override fun execute(argument: String): String {
        ListToFile(string, HashSetToList(labWorkCollections.getCollections()).list, labWorkCreator.getId()).fileWrite()
        return "Collection save"
    }
}