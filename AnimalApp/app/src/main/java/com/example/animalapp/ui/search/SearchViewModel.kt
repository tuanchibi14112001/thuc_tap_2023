package com.example.animalapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.SearchDetail
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: AnimalTypeRepo) :
    BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<SearchDetail>>()
    private val _dataSpecieFlow = MutableLiveData<Resource<AnimalSpecieItem>>()

    val dataFlow: LiveData<Resource<SearchDetail>>
        get() = _dataFlow
    val dataSpecieFlow: LiveData<Resource<AnimalSpecieItem>>
        get() = _dataSpecieFlow

    fun getPrepareSearch() = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getPrepareSearch()
        _dataFlow.value = result
    }

    fun getAnimalSpecie(animalName: String) = viewModelScope.launch {
        _dataSpecieFlow.value = Resource.loading()
        val result = repo.getMoreInfo(animalName)
        _dataSpecieFlow.value = result
    }
}