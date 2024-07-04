package com.example.animalapp.model

import java.io.Serializable

data class AnimalBreedItem(
    val animal_specie_id: Int,
    val animal_length: String,
    val animal_tail: String,
    val animal_weight: String,
    val average_lifespan: String,
    val comments: String,
    val id: Int,
    val img_url: String,
    val name: String,
    val is_liked: Boolean,
) : Serializable