package com.example.animalapp.model

import java.io.Serializable

data class AnimalFamilyItem(
    val animal_type_id: Int,
    val desc: String,
    val id: Int,
    val img_url: String,
    val name: String,
) : Serializable