package com.example.animalapp.api

import com.example.animalapp.model.AnimalType
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("animal_type")
    fun getAnimalType(): Call<List<AnimalType>>
}
