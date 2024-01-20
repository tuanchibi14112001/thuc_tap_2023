package com.example.animalapp.ui.animal_specie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimalSpeciesViewModel @Inject constructor(private val repo: AnimalTypeRepo): BaseViewModel(){
    private val _dataFlow = MutableLiveData<Resource<AnimalSpecieItem>>()
    val dataFlow : LiveData<Resource<AnimalSpecieItem>>
    get() = _dataFlow

    fun getAnimalSpecies(id: Int) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getAnimalSpecies(id)
        _dataFlow.value = result
    }
}