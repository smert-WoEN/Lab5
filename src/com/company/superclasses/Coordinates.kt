package com.company.superclasses

import java.io.Console
import javax.swing.text.StyledEditorKit

interface Coordinates {
    fun getCoordinates(csv: Boolean = false) : String
}