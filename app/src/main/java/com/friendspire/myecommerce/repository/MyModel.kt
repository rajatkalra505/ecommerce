package com.friendspire.myecommerce.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.friendspire.myecommerce.data.MyDataResponse

class MyModel : ViewModel() {
    val dummyLiveData = MutableLiveData<List<MyDataResponse>>()
    private var apiError = MutableLiveData<String>()
    private var onFailure = MutableLiveData<Throwable>()
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
