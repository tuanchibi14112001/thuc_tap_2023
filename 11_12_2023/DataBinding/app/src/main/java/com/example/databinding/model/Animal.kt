package com.example.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.databinding.BR


class Animal ( private var name: String) : BaseObservable() {

    @Bindable
    fun getName() : String{
        return this.name
    }

    fun setName(s : String){
        this.name = s
        notifyPropertyChanged(BR.name)
    }

    fun changeName(){
        this.name = "CAT"
        notifyPropertyChanged(BR.name)
    }



}