package com.example.animalapp.ui.animal_family_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalBreed
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyDetailViewModel @Inject constructor(private val repo: AnimalTypeRepo): BaseViewModel(){
    private val _dataFlow = MutableLiveData<Resource<AnimalBreed>>()
    val dataFlow : LiveData<Resource<AnimalBreed>>
        get() = _dataFlow

    fun getAnimalBreed(id: Int) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getAnimalBreeds(id)
        _dataFlow.value = result
    }
}