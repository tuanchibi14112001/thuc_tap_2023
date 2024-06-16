package com.example.animalapp.ui.animal_family

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAnimalViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel(){
    private val _dataFlow = MutableLiveData<Resource<AnimalSpecie>>()
    val dataFlow : LiveData<Resource<AnimalSpecie>>
        get() = _dataFlow

    fun getAnimalFamily(name: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getAnimalFamily(name)
        _dataFlow.value = result
    }
}