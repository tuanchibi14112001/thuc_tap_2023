package com.example.animalapp.ui.gallery.gallery_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.GalleryDetail
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(private val repo: AnimalTypeRepo) :
    BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<GalleryDetail>>()
    val dataFlow: LiveData<Resource<GalleryDetail>>
        get() = _dataFlow

    fun getGalleryDetail(token: String, animal_specie_name: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getGalleryDetail(token, animal_specie_name)
        _dataFlow.value = result
    }
}