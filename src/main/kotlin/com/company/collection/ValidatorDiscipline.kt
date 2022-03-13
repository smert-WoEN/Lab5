package com.company.collection

import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.maxLabsCount
import com.company.superclasses.DisciplineValidate
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.minLectureHours
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.maxLectureHours
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.maxPracticeHours
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.maxSelfStudyHours
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.minLabsCount
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.minPracticeHours
import com.company.collection.ValidatorDiscipline.DisciplineValidatorConfig.minSelfStudyHours

class ValidatorDiscipline: DisciplineValidate{
    internal object DisciplineValidatorConfig{
        var minLectureHours = 0L
        var maxLectureHours = Long.MAX_VALUE
        var minPracticeHours = 0
        var maxPracticeHours = Int.MAX_VALUE
        var minLabsCount = 0
        var maxLabsCount = Int.MAX_VALUE
        var minSelfStudyHours = 0L
        var maxSelfStudyHours = Long.MAX_VALUE
    }
    override fun checkName(name: String): String {
        if (name.isBlank()){
            throw IllegalArgumentException("name empty")
        }
        else {
            return name
        }
    }

    override fun checkLectureHours(lectureHours: Long?): Long? {
        if (lectureHours == null || lectureHours in minLectureHours until maxLectureHours) {
            return lectureHours
        } else {
            throw IllegalArgumentException("lectureHours out of range [$minLectureHours, $maxLectureHours")
        }
    }

    override fun checkPracticeHours(practiceHours: Int): Int {
        if (practiceHours in minPracticeHours until maxPracticeHours) {
            return practiceHours
        } else {
            throw IllegalArgumentException("practiceHours out of range [$minPracticeHours, $maxPracticeHours")
        }
    }

    override fun checkSelfStudyHours(selfStudyHours: Long): Long {
        if (selfStudyHours in minSelfStudyHours until maxSelfStudyHours) {
            return selfStudyHours
        } else {
            throw IllegalArgumentException("selfStudyHours out of range [$minSelfStudyHours, $maxSelfStudyHours")
        }
    }

    override fun checkLabsCount(labsCount: Int): Int {
        if (labsCount in minLabsCount until maxLabsCount) {
            return labsCount
        } else {
            throw IllegalArgumentException("labsCount out of range [$minLabsCount, $maxLabsCount")
        }
    }

    override fun checkDiscipline(
        name: String,
        lectureHours: Long?,
        practiceHours: Int,
        selfStudyHours: Long,
        labsCount: Int
    ): Discipline {
        return Discipline(checkName(name), checkLectureHours(lectureHours), checkPracticeHours(practiceHours), checkSelfStudyHours(selfStudyHours), checkLabsCount(labsCount))
    }
}