package com.example.animalapp.ui.auth_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalapp.base.BaseViewModel
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.repository.AnimalTypeRepo
import com.example.animalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AnimalTypeRepo) : BaseViewModel() {
    private val _dataFlow = MutableLiveData<Resource<AuthResponse>>()
    val dataFlow : LiveData<Resource<AuthResponse>>
    get() = _dataFlow

    fun loginUser(email: String, pwd: String) = viewModelScope.launch {
        _dataFlow.value = Resource.loading()
        val result = repo.loginUser(email,pwd)
        _dataFlow.value = result
    }
}