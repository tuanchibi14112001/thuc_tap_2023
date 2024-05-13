package com.example.animalapp.model

data class AuthResponse(
    val message: String,
    val status: Boolean,
    val token: String
)