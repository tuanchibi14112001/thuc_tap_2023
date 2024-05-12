package com.example.animalapp.ui.auth_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.LoginResponse
import com.example.animalapp.model.User
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<User>>()
    val dataFlow : LiveData<Resource<User>>
        get() = _dataFlow

    fun getUser(token: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.getUser(token)
        _dataFlow.value = result
    }


}