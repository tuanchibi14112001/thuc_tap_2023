package com.example.animalapp.api

import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.TestModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiAiService {
    @GET("/")
    fun getTest(): Call<TestModel>

    @Multipart
    @POST("/filename")
    fun getFileName(
        @Part image: MultipartBody.Part
    ): Call<TestModel>

    @Multipart
    @POST("/file")
    fun getAnimalNamePre(
        @Part image: MultipartBody.Part
    ): Call<AnimalPredictResult>
}