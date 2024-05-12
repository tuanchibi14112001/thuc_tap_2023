package com.example.animalapp.repository

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.LoginResponse
import com.example.animalapp.model.User
import com.example.animalapp.utils.Resource

interface AnimalTypeRepo {
    suspend fun loginUser(email: String, pwd: String): Resource<LoginResponse>
    suspend fun getUser(token: String): Resource<User>
    suspend fun getAnimalType(): Resource<List<AnimalType>>
    suspend fun getAnimalFamily(name: String): Resource<AnimalFamily>
    suspend fun getAnimalBreeds(id: Int): Resource<AnimalSpecie>
    suspend fun getAnimalSpecies(id: Int): Resource<AnimalSpecieItem>
    suspend fun getMemoryCard(): Resource<MemoryCard>
}