package com.example.animalapp.model

import java.io.Serializable

data class AnimalSpecieItem(
    val animal_family_id: Int,
    val animal_length: String,
    val animal_tail: String,
    val animal_weight: String,
    val average_lifespan: String,
    val comments: String,
    val id: Int,
    val img_url: String,
    val name: String,
) : Serializable