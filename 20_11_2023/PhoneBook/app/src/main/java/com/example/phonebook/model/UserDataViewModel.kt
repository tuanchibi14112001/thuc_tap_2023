package com.example.phonebook.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {
    var userLiveData: MutableLiveData<MutableList<UserData>>

    init {
        userLiveData = MutableLiveData<MutableList<UserData>>()
        userLiveData.value = mutableListOf()
    }

    fun getSize(): Int {
        return userLiveData.value?.size ?: 0
    }

    fun getUserList() : MutableList<UserData>{
        return  userLiveData.value ?: mutableListOf()
    }



    fun addUser(name: String, num: String) {
        userLiveData.value?.add(UserData(name,num))
    }

    fun removeUser(position: Int){
        userLiveData.value?.removeAt(position)
    }
}