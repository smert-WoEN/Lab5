package com.company.files

import com.company.legacy.LabWork

class ListToHashSet(list: ArrayList<LabWork>) {
    val hashSet = HashSet<LabWork>(list)
}