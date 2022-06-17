package com.company.server.commands

import com.company.collection.LabWorkCollections
import com.company.collection.LabWorkComparator

class RemoveID(private val labWorkComparator: LabWorkComparator): Command {
    override fun getLabel(): String {
        return "remove"
    }

    override fun getDescription(): String {
        return "remove element from collection by id"
    }

    /**
     * Execute the command
     *
     * @param any command arguments
     * @return Command execution result
     */
    override fun execute(any: Any): String {
        return if (any is String) {
            try {
                val args = any.split(" ", limit = 2)
                if (labWorkComparator.removeLabWork(args[0].toInt(), args[1])) {
                    "remove successful"
                } else {
                    "can't remove"
                }
            } catch (e: IllegalArgumentException) {
                e.message!!
            }
            catch (e: RuntimeException) {
                e.message!!
            }
        } else {
            "bad element collection, try again"
        }    }
}