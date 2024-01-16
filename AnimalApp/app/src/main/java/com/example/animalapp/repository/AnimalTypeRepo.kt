package com.example.animalapp.repository

import com.example.animalapp.model.AnimalType
import com.example.animalapp.utils.Resource

interface AnimalTypeRepo {
    suspend fun getAnimalType() : Resource<List<AnimalType>>
}