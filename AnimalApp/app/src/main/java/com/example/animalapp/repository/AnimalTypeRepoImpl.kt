package com.example.animalapp.repository

import com.example.animalapp.api.ApiService
import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalType
import com.example.animalapp.utils.Resource
import retrofit2.await
import javax.inject.Inject

class AnimalTypeRepoImpl @Inject constructor(private val apiService: ApiService) : AnimalTypeRepo {
    override suspend fun getAnimalType(): Resource<List<AnimalType>> {
        return try {
            val result = apiService.getAnimalType().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getAnimalFamily(name: String): Resource<AnimalFamily> {
        return try {
            val result = apiService.getAnimalFamily(name).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }
}