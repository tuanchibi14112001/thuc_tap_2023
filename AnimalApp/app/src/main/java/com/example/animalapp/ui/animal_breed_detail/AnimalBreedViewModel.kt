package com.example.animalapp.ui.animal_breed_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.model.LikeResponse
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimalBreedViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<AnimalBreedItem>>()
    private val _likeDataFlow = MutableLiveData<Resource<LikeResponse>>()
    private val _unlikeDataFlow = MutableLiveData<Resource<LikeResponse>>()


    val dataFlow: LiveData<Resource<AnimalBreedItem>>
        get() = _dataFlow

    val likeDataFlow: LiveData<Resource<LikeResponse>>
        get() = _likeDataFlow

    val unlikeDataFlow: LiveData<Resource<LikeResponse>>
        get() = _unlikeDataFlow

    fun getAnimalBreedDetail(token: String, id: Int) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getAnimalBreedDetail(token, id)
        _dataFlow.value = result
    }

    fun postLikeBreed(token: String, id: Int) = viewModelScope.launch {
        _likeDataFlow.value = Resource.loading()
        val result = repo.postLikeBreed(token, id)
        _likeDataFlow.value = result
    }

    fun postUnlikeBreed(token: String, id: Int) = viewModelScope.launch {
        _unlikeDataFlow.value = Resource.loading()
        val result = repo.postUnlikeBreed(token, id)
        _unlikeDataFlow.value = result
    }
}