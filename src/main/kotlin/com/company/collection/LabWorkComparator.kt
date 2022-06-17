package com.company.collection

import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

class LabWorkComparator(private val labWorkCollections: LabWorkCollections,
                        private val labWorkBD: LabWorkBD) {
    private val lock: ReadWriteLock = ReentrantReadWriteLock()

    fun updateById(id: Int, labWorkClient: LabWorkClient) {
        lock.writeLock().lock()
        try {
            labWorkCollections.removeLabWork(id, labWorkClient.owner)
            labWorkCollections.addLabWork(labWorkBD.updateById(id, labWorkClient))
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun getInitDate() = labWorkCollections.dateInInitialize

    fun getCountElements(): Int {
        lock.readLock().lock()
        val count: Int
        try {
            count = labWorkCollections.getCountElements()
        } finally {
            lock.readLock().unlock()
        }
        return count
    }

    fun setFromBD(){
        lock.writeLock().lock()
        try {
            labWorkCollections.setFromList(labWorkBD.getLabWorks())
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun clearElements(owner: String) {
        lock.writeLock().lock()
        try {
            labWorkCollections.clearCollection(owner)
            labWorkBD.removeByOwner(owner)
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun getCollections(): HashSet<LabWork>{
        lock.readLock().lock()
        val collections: HashSet<LabWork>
        try {
            collections = labWorkCollections.getCollections()
        } finally {
            lock.readLock().unlock()
        }
        return collections
    }

    fun addLabWork(labWorkClient: LabWorkClient) {
        lock.writeLock().lock()
        val validateLabWork = ValidatorLabWork()
        val validatorCoordinates = ValidatorCoordinates()
        val validatorDiscipline = ValidatorDiscipline()
        try {
            labWorkBD.addElement(LabWorkClient(validateLabWork.checkName(labWorkClient.name),
            validatorCoordinates.checkCoordinates(labWorkClient.coordinates.x, labWorkClient.coordinates.y),
            validateLabWork.checkMinimalPoint(labWorkClient.minimalPoint),
            validateLabWork.checkMaximalPoint(labWorkClient.maximalPoint),
                labWorkClient.difficulty,
            validatorDiscipline.checkDiscipline(labWorkClient.discipline.name,
            labWorkClient.discipline.lectureHours,
            labWorkClient.discipline.practiceHours,
            labWorkClient.discipline.selfStudyHours,
            labWorkClient.discipline.labsCount), labWorkClient.owner
                ))
            labWorkCollections.addLabWork(labWorkBD.getLast())
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun removeLabWork(id: Int, owner: String): Boolean {
        lock.writeLock().lock()
        val flag: Boolean
        try {
            flag = labWorkCollections.removeLabWork(id, owner)
            if (flag) {
                labWorkBD.removeById(id, owner)
            }
        } finally {
            lock.writeLock().unlock()
        }
        return flag
    }

    fun findLabWork(id: Int, owner: String): LabWork {
        lock.readLock().lock()
        val labWork: LabWork
        try {
            labWork = labWorkCollections.findLabWork(id, owner)
        } finally {
            lock.readLock().unlock()
        }
        return labWork
    }

    fun findMaxPointValue(): Double {
        lock.readLock().lock()
        val maxSum: Double
        try {
            maxSum = labWorkCollections.findMaxPointValue()
        } finally {
            lock.readLock().unlock()
        }
        return maxSum
    }

    fun findMinPointValue(): Int {
        lock.readLock().lock()
        val minSum: Int
        try {
            minSum = labWorkCollections.findMinPointValue()
        } finally {
            lock.readLock().unlock()
        }
        return minSum
    }

    fun removeLessMinPoint(minPoint: Int, owner: String) {
        lock.writeLock().lock()
        try {
            labWorkCollections.removeLessMinPoint(minPoint, owner)
            labWorkBD.removeLessMinPoint(minPoint, owner)
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun sumMaxPoint(): Double {
        lock.readLock().lock()
        val sumMax: Double
        try {
            sumMax = labWorkCollections.sumMaxPoint()
        } finally {
            lock.readLock().unlock()
        }
        return sumMax
    }

    fun findLabWorkCompareMaxPoint(maxPoint: Double): Set<LabWork> {
        lock.readLock().lock()
        val labWork: Set<LabWork>
        try {
            labWork = labWorkCollections.findLabWorkCompareMaxPoint(maxPoint)
        } finally {
            lock.readLock().unlock()
        }
        return labWork
    }

    fun groupMinPoint(): Map<Int, List<LabWork>> {
        lock.readLock().lock()
        val labWork: Map<Int, List<LabWork>>
        try {
            labWork = labWorkCollections.groupMinPoint()
        } finally {
            lock.readLock().unlock()
        }
        return labWork
    }
}