package com.example.animalapp.api

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.LoginResponse
import com.example.animalapp.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") pwd: String): Call<LoginResponse>

    @GET("user")
    fun getUser(@Header("Authorization") auth:String): Call<User>

    @GET("animal_type")
    fun getAnimalType(): Call<List<AnimalType>>

    @GET("animal_type/{animal_name}")
    fun getAnimalFamily(@Path("animal_name") animal_name: String): Call<AnimalFamily>

    @GET("animal_species/{id}")
    fun getAnimalSpecies(@Path("id") id: Int): Call<AnimalSpecieItem>

    @GET("animal_family/{id}")
    fun getAnimalBreeds(@Path("id") id: Int): Call<AnimalSpecie>

    @GET("play")
    fun getMemoryCard(): Call<MemoryCard>
}
