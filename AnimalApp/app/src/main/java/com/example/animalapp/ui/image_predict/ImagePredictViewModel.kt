package com.example.animalapp.ui.image_predict

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.TestModel
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ImagePredictViewModel @Inject constructor(private val repo: AnimalTypeRepo) :
    BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<AnimalPredictResult>>()
    val dataFlow: LiveData<Resource<AnimalPredictResult>>
        get() = _dataFlow

    fun getAnimalNamePre(part: MultipartBody.Part) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getAnimalNamePre(part)
        _dataFlow.value = result
    }
}