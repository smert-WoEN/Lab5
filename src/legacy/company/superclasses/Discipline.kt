package legacy.company.superclasses

interface Discipline {
        fun getDiscipline(cvs: Boolean = false): String
        val name: String
        var lectureHours: Long?
        var practiceHours: Int
        var selfStudyHours: Long
        var labsCount: Int
}