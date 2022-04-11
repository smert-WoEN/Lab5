package com.company.ui

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkCreator
import com.company.commands.*
import java.lang.Runnable


/**
 * Main command execution runnable
 */
class UserRunnable(
    labWorkCollections: LabWorkCollections,
    labWorkCreator: LabWorkCreator,
    string: String
) : Runnable {

    /**
     * All possible commands
     */
    val allCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        ExecuteScript(labWorkCollections,labWorkCreator,string, this),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    /**
     * Commands that user can use
     */
    val userCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        ExecuteScript(labWorkCollections,labWorkCreator,string, this),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    /**
     * Programs that execute_script can use
     */
    val scriptCommands = arrayOf(
        Help(this),
        Info(labWorkCollections),
        Show(labWorkCollections),
        UpdateID(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        Save(labWorkCollections, labWorkCreator, string),
        //Execute_script(),
        Exit(),
        Add(labWorkCreator, labWorkCollections),
        AddIfMin(labWorkCreator, labWorkCollections),
        AddIfMax(labWorkCreator, labWorkCollections),
        Clear(labWorkCollections),
        FilterMaxPoint(labWorkCollections),
        GroupMinPoint(labWorkCollections),
        RemoveID(labWorkCollections),
        RemoveLower(labWorkCollections),
        SumMaxPoint(labWorkCollections)
    )

    override fun run() {
        TODO("Not yet implemented")
    }


}