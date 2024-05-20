package com.example.animalapp.api

import com.example.animalapp.model.TestModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiAiService {
    @GET("/")
    fun getTest(): Call<TestModel>
}