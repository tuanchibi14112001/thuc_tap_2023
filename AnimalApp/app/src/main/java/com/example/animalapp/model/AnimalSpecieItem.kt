package com.example.animalapp.model

import java.io.Serializable

data class AnimalSpecieItem(
    val animal_type_id: Int,
    val desc: String,
    val id: Int,
    val img_url: String,
    val is_exist: Int,
    val name: String,
) : Serializable