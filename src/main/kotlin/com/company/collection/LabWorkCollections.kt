package com.company.collection

import java.util.Date
import java.util.concurrent.ConcurrentHashMap

class LabWorkCollections {

    fun setFromList(hashSet: HashSet<LabWork>) {
        labWorkCollections.addAll(hashSet)
    }

    val dateInInitialize = Date()
    private var concurrentHashMap = ConcurrentHashMap<LabWork, String>()
    private var labWorkCollections: MutableSet<LabWork> = concurrentHashMap.keySet("SET-ENTRY")
    //private var labWorkCollections = HashSet<LabWork>()

    fun clearCollection() {
        labWorkCollections.clear()
    }

    fun getCountElements() = labWorkCollections.size

    @Suppress("UNCHECKED_CAST")
    fun getCollections(): HashSet<LabWork> = HashSet(labWorkCollections)

    fun addLabWork(labWork: LabWork) {
        labWorkCollections.add(labWork)
        println(labWorkCollections)
    }

//    fun updateLabWork(id: Int) {
//        val remove = removeLabWork(id)
//    }

    fun removeLabWork(id: Int): Boolean = labWorkCollections.removeIf { it.id == id}

    fun findLabWork(id: Int): LabWork = labWorkCollections.find { it.id == id } ?: throw IllegalArgumentException("labWork with id $id not found.")

    fun findMaxPointValue(): Double = labWorkCollections.maxOf { it.maximalPoint }

    fun findMinPointValue(): Int = labWorkCollections.minOf { it.minimalPoint }

    fun removeLessMinPoint(minPoint: Int) {
        labWorkCollections.removeIf { it.minimalPoint < minPoint }
    }

    fun sumMaxPoint(): Double = labWorkCollections.sumOf { it.maximalPoint }

    fun findLabWorkCompareMaxPoint(maxPoint: Double): HashSet<LabWork> = labWorkCollections.filter { it.maximalPoint > maxPoint}.toHashSet()

    fun groupMinPoint(): Map<Int, List<LabWork>> = labWorkCollections.groupBy { it.minimalPoint }

}