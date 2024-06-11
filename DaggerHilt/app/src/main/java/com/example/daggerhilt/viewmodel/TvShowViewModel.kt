package com.example.daggerhilt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhilt.models.TVShowItem
import com.example.daggerhilt.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject constructor(private val repository: TvShowRepository ) : ViewModel() {

    private val _response = MutableLiveData<List<TVShowItem>>()
    val responseTvShow: LiveData<List<TVShowItem>> get() = _response


    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch() {
        try {
            repository.getTvShow().let { response ->
                if (response.isSuccessful) {
                    _response.postValue(response.body())
                } else {
                    Log.d("tag", "getAllTvShows Error: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Log.d("tag", "getAllTvShows Error: ${e.toString()}")

        }
    }
}