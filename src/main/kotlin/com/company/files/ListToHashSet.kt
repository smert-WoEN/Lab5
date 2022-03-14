package com.company.files

import com.company.collection.LabWork

class ListToHashSet(list: ArrayList<LabWork>) {
    val hashSet = HashSet<LabWork>(list)
}