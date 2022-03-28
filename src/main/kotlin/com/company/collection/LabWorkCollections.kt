package com.company.collection

import java.util.TreeSet

class LabWorkCollections{
    val labWorkCollections = HashSet<LabWork>()
    lateinit var labWork: LabWork

    fun removeLabWork(id: Int) {
        labWorkCollections.remove(findLabWork(id))
    }

    fun findLabWork(id: Int): LabWork {
        for (labWork in labWorkCollections) {
            if (labWork.id == id) {
                this.labWork = labWork
                return labWork
            }
        }
        throw IllegalArgumentException("labWork with id $id not found.")
    }

    fun findMaxPointValue(): Double {
        var maxPoint = 0.0
        for (labWork in labWorkCollections) {
            if (maxPoint < labWork.maximalPoint) {
                maxPoint = labWork.maximalPoint
            }
        }
        return maxPoint
    }

    fun findMinPointValue(): Int {
        var minPoint = Int.MAX_VALUE
        for (labWork in labWorkCollections) {
            if (minPoint > labWork.minimalPoint) {
                minPoint = labWork.minimalPoint
            }
        }
        return minPoint
    }

    fun removeLessMinPoint(minPoint: Int) {
        val labWorkCollections = HashSet<LabWork>()
        for (labWork in this.labWorkCollections) {
            if (labWork.minimalPoint < minPoint) {
                labWorkCollections.add(labWork.copy())
            }
        }
        for (labWork in labWorkCollections) {
            this.labWorkCollections.remove(labWork)
        }
    }

    fun sumMaxPoint(): Double {
        var sum = 0.0
        for (labWork in labWorkCollections) {
            sum += labWork.maximalPoint
        }
        return sum
    }

    fun findLabWorkCompareMaxPoint(maxPoint: Double): HashSet<LabWork> {
        val labWorkCollections = HashSet<LabWork>()
        for (labWork in this.labWorkCollections) {
            if (labWork.maximalPoint == maxPoint) {
                labWorkCollections.add(labWork.copy())
            }
        }
        return labWorkCollections
    }

    fun groupMinPoint(): HashMap<Int, Int> {
        val minPoint = TreeSet<Int>()
        for (labWork in labWorkCollections) {
            minPoint.add(labWork.minimalPoint)
        }
        val mapPoint = HashMap<Int, Int>()
        for (p in minPoint) {
            mapPoint[p] = 0
        }
        for (labWork in labWorkCollections) {
            val point = mapPoint[labWork.minimalPoint]!!
            mapPoint[labWork.minimalPoint] = (point + 1)
        }
        return mapPoint
    }
}