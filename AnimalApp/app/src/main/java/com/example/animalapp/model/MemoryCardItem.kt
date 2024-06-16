package com.example.animalapp.model

data class MemoryCardItem(
    val animal_specie_id: Int,
    val id: Int,
    val img_url: String,
    var is_checked: Boolean,
    var is_clicked: Boolean,
    val name: String
)