package com.example.animalapp.model

data class LoginResponse(
    val message: String,
    val status: Boolean,
    val token: String
)