package com.company.legacy

import com.company.collection.Coordinates
import com.company.collection.Discipline
import com.company.superclasses.Difficulty
import java.util.Date

@Deprecated("")
data class LabWork(val id: Int, val name: String, val coordinates: Coordinates,
                   val creationDate: Date, val minimalPoint: Int,
                   val maximalPoint: Double, val difficulty: Difficulty,
                   val discipline: Discipline
): Comparable<LabWork>, java.io.Serializable {
    /**
     * LabWork-to-LabWork comparator
     *
     * @param other LabWork
     * @return >0 if this LabWork have more maximalPoint, <0 if LabWork have less maximalPoint, 0 if LabWork are of equal maximalPoint
     */
    override fun compareTo(other: LabWork): Int {
        return (this.maximalPoint - other.maximalPoint).toInt()
    }
//Comporator
}
