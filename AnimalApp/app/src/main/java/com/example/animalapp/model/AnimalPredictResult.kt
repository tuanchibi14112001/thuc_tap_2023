package com.example.animalapp.model

data class AnimalPredictResult(
    val result: String,
    val similar: List<String>
)