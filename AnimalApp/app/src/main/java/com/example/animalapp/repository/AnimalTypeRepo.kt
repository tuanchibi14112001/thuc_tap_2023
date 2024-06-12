package com.example.animalapp.repository

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.TestModel
import com.example.animalapp.model.User
import com.example.animalapp.utils.Resource
import okhttp3.MultipartBody

interface AnimalTypeRepo {
    suspend fun loginUser(email: String, pwd: String): Resource<AuthResponse>
    suspend fun registerUser(name: String, email: String, pwd: String): Resource<AuthResponse>

    suspend fun getUser(token: String): Resource<User>
    suspend fun getAnimalType(): Resource<List<AnimalType>>
    suspend fun getAnimalFamily(name: String): Resource<AnimalFamily>
    suspend fun getAnimalBreeds(id: Int): Resource<AnimalSpecie>
    suspend fun getMoreInfo(animalf_name: String): Resource<MoreInfo>
    suspend fun  getOtherResults(other_results: List<String>): Resource<AnimalFamily>
    suspend fun getAnimalSpecies(id: Int): Resource<AnimalSpecieItem>
    suspend fun getMemoryCard(): Resource<MemoryCard>

    suspend fun getQuizz(): Resource<Quizz>

    suspend fun getTest(): Resource<TestModel>
    suspend fun getFileName(part: MultipartBody.Part): Resource<TestModel>

    suspend fun getAnimalNamePre(part: MultipartBody.Part): Resource<AnimalPredictResult>
}