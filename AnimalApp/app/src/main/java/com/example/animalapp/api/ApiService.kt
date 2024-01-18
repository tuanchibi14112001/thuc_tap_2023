package com.example.animalapp.api

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("animal_type")
    fun getAnimalType(): Call<List<AnimalType>>

    @GET("animal_type/{animal_name}")
    fun getAnimalFamily(@Path("animal_name") animal_name: String): Call<AnimalFamily>

    @GET("animal_family/{id}")
    fun getAnimalBreeds(@Path("id") id: Int): Call<AnimalSpecie>
}
