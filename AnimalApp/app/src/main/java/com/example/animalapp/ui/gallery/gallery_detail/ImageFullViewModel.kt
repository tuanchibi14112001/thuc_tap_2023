package com.example.animalapp.ui.gallery.gallery_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.ImageResponse
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageFullViewModel @Inject constructor(private val repo: AnimalTypeRepo) :
    BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<ImageResponse>>()
    val dataFlow: LiveData<Resource<ImageResponse>>
        get() = _dataFlow

    fun deleteImageGallery(token: String, image_id: Int) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.deleteImageGallery(token, image_id)
        _dataFlow.value = result
    }
}