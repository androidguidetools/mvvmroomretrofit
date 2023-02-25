package com.hlink.mvvmroomretorfit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel:ViewModel() {

    val factLiveData = MutableLiveData<String>("This is fact")
}