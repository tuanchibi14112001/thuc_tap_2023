package com.example.demoviewmodel.MViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var mCountNum = MutableLiveData<Int>()

    init {
        mCountNum.value = 0
    }

    fun updateNum(){
        mCountNum.value = mCountNum.value?.plus(1)
    }

}