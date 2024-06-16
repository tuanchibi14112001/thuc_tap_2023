package com.example.animalapp.repository

import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.AnimalBreed
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.TestModel
import com.example.animalapp.model.UploadImageResponse
import com.example.animalapp.model.User
import com.example.animalapp.utils.Resource
import okhttp3.MultipartBody

interface AnimalTypeRepo {
    suspend fun loginUser(email: String, pwd: String): Resource<AuthResponse>
    suspend fun registerUser(name: String, email: String, pwd: String): Resource<AuthResponse>

    suspend fun getUser(token: String): Resource<User>
    suspend fun postImageToGallery(
        token: String,
        animal_specie_id: Int,
        part: MultipartBody.Part
    ): Resource<UploadImageResponse>

    suspend fun getAnimalType(): Resource<List<AnimalType>>
    suspend fun getAnimalSpecies(name: String): Resource<AnimalSpecie>
    suspend fun getAnimalBreeds(id: Int): Resource<AnimalBreed>
    suspend fun getMoreInfo(animalf_name: String): Resource<MoreInfo>
    suspend fun getOtherResults(other_results: List<String>): Resource<AnimalSpecie>
    suspend fun getAnimalBreedDetail(id: Int): Resource<AnimalBreedItem>
    suspend fun getMemoryCard(): Resource<MemoryCard>

    suspend fun getQuizz(): Resource<Quizz>

    suspend fun getTest(): Resource<TestModel>
    suspend fun getFileName(part: MultipartBody.Part): Resource<TestModel>

    suspend fun getAnimalNamePre(part: MultipartBody.Part): Resource<AnimalPredictResult>
}