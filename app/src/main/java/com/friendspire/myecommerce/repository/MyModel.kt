package com.friendspire.myecommerce.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.friendspire.myecommerce.data.MyDataResponse

class MyModel : ViewModel() {
    val dummyLiveData = MutableLiveData<List<MyDataResponse>>()
    var apiError = MutableLiveData<String>()
    var onFailure = MutableLiveData<Throwable>()
     fun getMydata() {
        MyRepository.getData({
            dummyLiveData.postValue(it)

        },
            {
                apiError.value = it
            },
            {
                onFailure.value = it
            })
    }

}
