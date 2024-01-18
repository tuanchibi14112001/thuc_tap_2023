package com.example.animalapp.repository

import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalType
import com.example.animalapp.utils.Resource

interface AnimalTypeRepo {
    suspend fun getAnimalType() : Resource<List<AnimalType>>
    suspend fun getAnimalFamily(name: String): Resource<AnimalFamily>

    suspend fun getAnimalBreeds(id: Int): Resource<AnimalSpecie>
}