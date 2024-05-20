package com.example.animalapp.ui.image_predict

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.TestModel
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePredictViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel(){
    private val _dataFlow = MutableLiveData<Resource<TestModel>>()
    val dataFlow : LiveData<Resource<TestModel>>
        get() = _dataFlow
    fun getTest() = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getTest()
        _dataFlow.value = result
    }
}