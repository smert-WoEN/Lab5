package com.company.files

import com.company.legacy.LabWork

class HashSetToList(hashSet: HashSet<LabWork>) {
    val list = ArrayList<LabWork>(hashSet)
}