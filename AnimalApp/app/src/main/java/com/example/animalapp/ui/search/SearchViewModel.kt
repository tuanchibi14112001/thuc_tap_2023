package com.example.animalapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
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
    val dataFlow: LiveData<Resource<SearchDetail>>
        get() = _dataFlow

    fun getPrepareSearch() = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getPrepareSearch()
        _dataFlow.value = result
    }
}