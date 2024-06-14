package com.example.animalapp.ui.image_predict

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.model.UploadImageResponse
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ResultInfoViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<MoreInfo>>()
    private val _otherResultDataFlow = MutableLiveData<Resource<AnimalFamily>>()
    private val _uploadResultDataFlow = MutableLiveData<Resource<UploadImageResponse>>()

    val dataFlow: LiveData<Resource<MoreInfo>>
        get() = _dataFlow
    val otherResultDataFlow: LiveData<Resource<AnimalFamily>>
        get() = _otherResultDataFlow
    val uploadImageDataFlow: LiveData<Resource<UploadImageResponse>>
        get() = _uploadResultDataFlow

    fun getMoreInfo(animalf_name: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getMoreInfo(animalf_name)
        _dataFlow.value = result
    }

    fun getOtherResults(other_results: List<String>) = viewModelScope.launch {
        _otherResultDataFlow.value = Resource.loading()
        val result = repo.getOtherResults(other_results)
        _otherResultDataFlow.value = result
    }

    fun postImageToGallery(
        token: String,
        animal_family_id: Int,
        part: MultipartBody.Part
    ) = viewModelScope.launch {
        _uploadResultDataFlow.value = Resource.loading()
        val result = repo.postImageToGallery(token, animal_family_id, part)
        _uploadResultDataFlow.value = result
    }

}