package com.example.animalapp.ui.memory_game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryGameViewModel @Inject constructor(private val repo: AnimalTypeRepo): BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<MemoryCard>>()
    val dataFlow : LiveData<Resource<MemoryCard>>
        get() = _dataFlow

    fun getMemoryCard() = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getMemoryCard()
        _dataFlow.value = result
    }
}