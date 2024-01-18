package com.example.animalapp.ui.animal_family

import com.example.animalapp.model.AnimalFamilyItem
import com.example.animalapp.model.AnimalSpecieItem

interface ItemClickListener {
    fun itemOnClick (animalFamilyItem: AnimalFamilyItem)
}