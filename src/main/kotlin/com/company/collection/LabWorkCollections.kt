package com.company.collection

import java.util.Date
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors

class LabWorkCollections {

    fun setFromList(hashSet: HashSet<LabWork>) {
        labWorkCollections.addAll(hashSet)
    }

    val dateInInitialize = Date()
    //private var concurrentHashMap = ConcurrentHashMap<LabWork, Boolean>()
    //private var labWorkCollections: MutableSet<LabWork> = ConcurrentHashMap.newKeySet()//.keySet("SET-ENTRY")
    private var labWorkCollections = HashSet<LabWork>()
    @Deprecated("")
    fun clearCollection() {
        labWorkCollections.clear()
    }

    fun clearCollection(owner: String) {
        labWorkCollections.removeAll { it.owner == owner }//removeIf { it.owner == owner }
    }
    fun getCountElements() = labWorkCollections.size

    @Suppress("UNCHECKED_CAST")
    fun getCollections(): HashSet<LabWork> = HashSet(labWorkCollections)

    fun addLabWork(labWork: LabWork) {
        labWorkCollections.add(labWork)
    }

//    fun updateLabWork(id: Int) {
//        val remove = removeLabWork(id)
//    }
    @Deprecated("")
    fun removeLabWork(id: Int): Boolean = labWorkCollections.removeIf { it.id == id}

    fun removeLabWork(id: Int, owner: String): Boolean = labWorkCollections.removeIf { it.id == id && it.owner == owner }
    @Deprecated("")
    fun findLabWork(id: Int): LabWork = labWorkCollections.find { it.id == id } ?: throw IllegalArgumentException("labWork with id $id not found.")

    fun findLabWork(id: Int, owner: String): LabWork = labWorkCollections.find {
        it.id == id && it.owner == owner
    } ?: throw IllegalArgumentException("labWork with id $id not found.")
    fun findMaxPointValue(): Double = labWorkCollections.maxOf { it.maximalPoint }

    fun findMinPointValue(): Int = labWorkCollections.minOf { it.minimalPoint }
    @Deprecated("")
    fun removeLessMinPoint(minPoint: Int) {
        labWorkCollections.removeIf { it.minimalPoint < minPoint }
    }

    fun removeLessMinPoint(minPoint: Int, owner: String) {
        labWorkCollections.removeIf { it.minimalPoint < minPoint && it.owner == owner}
    }
    fun sumMaxPoint(): Double = labWorkCollections.sumOf { it.maximalPoint }


    fun findLabWorkCompareMaxPoint(maxPoint: Double): Set<LabWork> = labWorkCollections.stream()
        .filter { it.maximalPoint > maxPoint }
        .collect(Collectors.toSet())//labWorkCollections.filter { it.maximalPoint > maxPoint}.toHashSet()

    fun groupMinPoint(): Map<Int, List<LabWork>> = labWorkCollections.parallelStream()
        .collect(Collectors.groupingBy { it.minimalPoint } )//labWorkCollections.groupBy { it.minimalPoint }

}