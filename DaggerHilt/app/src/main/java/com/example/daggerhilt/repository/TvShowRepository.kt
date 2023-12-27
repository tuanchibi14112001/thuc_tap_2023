package com.example.daggerhilt.repository

import com.example.daggerhilt.api.ApiService
import javax.inject.Inject

class TvShowRepository
@Inject constructor(private val apiService: ApiService) {
    suspend fun getTvShow() = apiService.getTvShows()
}