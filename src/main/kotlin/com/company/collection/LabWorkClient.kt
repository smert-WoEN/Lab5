package com.company.collection

import com.company.superclasses.Difficulty

data class LabWorkClient(val name: String, val coordinates: Coordinates,
                         val minimalPoint: Int,
                         val maximalPoint: Double, val difficulty: Difficulty,
                         val discipline: Discipline): Comparable<LabWork>, java.io.Serializable {
    /**
     * LabWork-to-LabWork comparator
     *
     * @param other LabWork
     * @return >0 if this LabWork have more maximalPoint, <0 if LabWork have less maximalPoint, 0 if LabWork are of equal maximalPoint
     */
    override fun compareTo(other: LabWork): Int {
        return (this.maximalPoint - other.maximalPoint).toInt()
    }
}