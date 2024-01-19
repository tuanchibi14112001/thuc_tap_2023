package com.example.animalapp.model

data class MemoryCardItem(
    val animal_family_id: Int,
    val id: Int,
    val img_url: String,
    val is_checked: Int,
    var is_clicked: Int,
    val name: String
)