package com.company.files

import com.company.collection.LabWork

class LabWorkToString(private val labWork: LabWork): com.company.superclasses.LabWorkToString{
    override fun labWorkToString(csv: Boolean): String {
        return if (csv) {
            "" + labWork.id + ", " + labWork.name + ", " + labWork.coordinates.x + ", "  + labWork.coordinates.y + ", " + labWork.creationDate.time + ", " +
                    labWork.minimalPoint + ", " + labWork.maximalPoint + ", " + labWork.difficulty + ", " + labWork.discipline.name + ", " +
                    labWork.discipline.lectureHours + ", " + labWork.discipline.practiceHours + ", " + labWork.discipline.selfStudyHours + ", " +
                    labWork.discipline.labsCount

        } else {
            "" + labWork.id +  " " + labWork.name + " " + labWork.coordinates.x + " "  + labWork.coordinates.y + " " + labWork.creationDate.time + " " +
                    labWork.minimalPoint + " " + labWork.maximalPoint + " " + labWork.difficulty + " " + labWork.discipline.name + " " +
                    labWork.discipline.lectureHours + " " + labWork.discipline.practiceHours + " " + labWork.discipline.selfStudyHours + " " +
                    labWork.discipline.labsCount
        }
    }
}