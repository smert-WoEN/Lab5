package legacy.company.superclasses


interface Coordinates {
    var x: Int
    var y: Double
    fun getCoordinates(csv: Boolean = false) : String
}