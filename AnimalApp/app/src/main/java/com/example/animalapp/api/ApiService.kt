package com.example.animalapp.api

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.User
import retrofit2.Call
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
        @Field("password") pwd: String): Call<AuthResponse>

    @FormUrlEncoded
    @POST("auth/register")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") pwd: String
    ): Call<AuthResponse>

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

    @GET("animal_family/detail/{animalf_name}")
    fun getMoreInfo(@Path("animalf_name") animalf_name: String): Call<MoreInfo>

    @GET("play")
    fun getMemoryCard(): Call<MemoryCard>

    @GET("quizz")
    fun getQuizz(): Call<Quizz>
}
