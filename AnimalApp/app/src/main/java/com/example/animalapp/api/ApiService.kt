package com.example.animalapp.api

import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalBreed
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.SpecieGallery
import com.example.animalapp.model.UploadImageResponse
import com.example.animalapp.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


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

    @GET("user/gallery")
    fun getUserGallery(@Header("Authorization") auth:String): Call<SpecieGallery>

    @GET("animal_type")
    fun getAnimalType(): Call<List<AnimalType>>

    @GET("animal_type/{animal_name}")
    fun getAnimalSpecies(@Path("animal_name") animal_name: String): Call<AnimalSpecie>

    @GET("animal_breed/{id}")
    fun getAnimalBreedDetail(@Path("id") id: Int): Call<AnimalBreedItem>

    @GET("animal_specie/get_breeds/{id}")
    fun getAnimalBreeds(@Path("id") id: Int): Call<AnimalBreed>

    @GET("animal_specie/detail/{animalf_name}")
    fun getMoreInfo(@Path("animalf_name") animalf_name: String): Call<MoreInfo>

    @GET("animal_specie/result_detail")
    fun getOtherResults(@Query("name[]") other_results: List<String>): Call<AnimalSpecie>

    @GET("play")
    fun getMemoryCard(): Call<MemoryCard>

    @GET("quizz")
    fun getQuizz(): Call<Quizz>

    @Multipart
    @POST("user/gallery-upload")
    fun postImageToGallery(
        @Header("Authorization") auth:String,
        @Part("animal_specie_name") animal_specie_name: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<UploadImageResponse>


}
