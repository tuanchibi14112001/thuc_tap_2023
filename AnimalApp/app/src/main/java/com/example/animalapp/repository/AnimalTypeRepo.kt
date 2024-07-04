package com.example.animalapp.repository

import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.AnimalBreed
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.GalleryDetail
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.SpecieGallery
import com.example.animalapp.model.TestModel
import com.example.animalapp.model.ImageResponse
import com.example.animalapp.model.LikeResponse
import com.example.animalapp.model.SearchDetail
import com.example.animalapp.model.User
import com.example.animalapp.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AnimalTypeRepo {
    suspend fun loginUser(email: String, pwd: String): Resource<AuthResponse>
    suspend fun registerUser(name: String, email: String, pwd: String): Resource<AuthResponse>

    suspend fun getUser(token: String): Resource<User>
    suspend fun getUserGallery(token: String): Resource<SpecieGallery>
    suspend fun getGalleryDetail(token: String, animal_specie_name: String): Resource<GalleryDetail>

    suspend fun postImageToGallery(
        token: String,
        animal_specie_name: RequestBody,
        part: MultipartBody.Part
    ): Resource<ImageResponse>

    suspend fun deleteImageGallery(
        token: String,
        image_id: Int
    ): Resource<ImageResponse>

    suspend fun getAnimalType(): Resource<List<AnimalType>>
    suspend fun getAnimalSpecies(name: String): Resource<AnimalSpecie>
    suspend fun getAnimalBreeds(id: Int): Resource<AnimalBreed>
    suspend fun getMoreInfo(animalf_name: String): Resource<AnimalSpecieItem>
    suspend fun getOtherResults(other_results: List<String>): Resource<AnimalSpecie>
    suspend fun getAnimalBreedDetail(token: String, id: Int): Resource<AnimalBreedItem>
    suspend fun postLikeBreed(token: String, animal_breed_id: Int): Resource<LikeResponse>
    suspend fun postUnlikeBreed(token: String, animal_breed_id: Int): Resource<LikeResponse>
    suspend fun getMemoryCard(): Resource<MemoryCard>

    suspend fun getQuizz(): Resource<Quizz>

    suspend fun getTest(): Resource<TestModel>
    suspend fun getFileName(part: MultipartBody.Part): Resource<TestModel>

    suspend fun getAnimalNamePre(part: MultipartBody.Part): Resource<AnimalPredictResult>

    suspend fun getPrepareSearch(): Resource<SearchDetail>
}