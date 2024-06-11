package com.example.animalapp.model

import java.io.Serializable

data class AnimalPredictResult(
    val result: String,
    val similar: List<String>
) : Serializable