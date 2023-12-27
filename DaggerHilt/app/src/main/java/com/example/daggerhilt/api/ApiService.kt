package com.example.daggerhilt.api

import com.example.daggerhilt.helper.Constants
import com.example.daggerhilt.models.TVShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getTvShows(): Response<TVShowResponse>

}