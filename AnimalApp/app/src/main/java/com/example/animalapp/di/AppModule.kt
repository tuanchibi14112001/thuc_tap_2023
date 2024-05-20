package com.example.animalapp.di

import com.example.animalapp.api.ApiAiService
import com.example.animalapp.api.ApiService
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.repository.AnimalTypeRepoImpl
import com.example.animalapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

class AppModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): ApiAiService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_AI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiAiService::class.java)

    @Provides
    fun provideAnimalTypeRepo(animalTypeRepoImpl: AnimalTypeRepoImpl): AnimalTypeRepo =
        animalTypeRepoImpl

}