package com.example.animalapp.ui.quizz_game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.Quizz
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizzViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel(){
    private val _dataFlow = MutableLiveData<Resource<Quizz>>()
    val dataFlow : LiveData<Resource<Quizz>>
        get() = _dataFlow

    fun getQuizz() = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getQuizz()
        _dataFlow.value = result
    }
}