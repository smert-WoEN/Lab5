package com.company.superclasses

import com.company.collection.LabWork

interface FromFile {
    val id: Int
    val list: ArrayList<LabWork>
    val count: UInt
}