package com.example.databinding.model

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class UserModel(var userName: String, var userHandlerInterface: UserHandlerInterface){

    fun userModelOnClickUser(v: View) {
        userHandlerInterface.clickUser(v)
    }

    fun userModelOnClickNoParam(){
        userHandlerInterface.clickWithNoParam()
    }

    fun userModelOnClickWithParam(s : String){
        userHandlerInterface.clickWithParam(s)
    }


}

interface UserHandlerInterface {

    fun clickUser(v: View) {
    }

    fun clickWithNoParam(){
    }

    fun clickWithParam(s : String){

    }
}