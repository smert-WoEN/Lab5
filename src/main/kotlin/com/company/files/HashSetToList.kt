package com.company.files

import com.company.collection.LabWork

class HashSetToList(hashSet: HashSet<LabWork>) {
    val list = ArrayList<LabWork>(hashSet)
}