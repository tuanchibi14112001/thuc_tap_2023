package com.example.animalapp.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.SpecieGallery
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListSpecieImageViewModel @Inject constructor(private val repo: AnimalTypeRepo) :
    BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<SpecieGallery>>()
    val dataFlow: LiveData<Resource<SpecieGallery>>
        get() = _dataFlow

    fun getUserGallery(token: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getUserGallery(token)
        _dataFlow.value = result
    }
}