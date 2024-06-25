package com.example.animalapp.model

import java.io.Serializable

data class QuizzItem(
    val animal_specie_id: Int,
    val answers: List<String>,
    var clickedAnswer: String?,
    val correctAnswer: String,
    val id: Int,
    val img_url: String,
    val name: String,
    val score: Int,
    val type: Int
): Serializable