package com.friendspire.myecommerce.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.friendspire.myecommerce.api.RetrofitHelper
import com.friendspire.myecommerce.api.WebService
import com.friendspire.myecommerce.data.MyDataResponse

class MyModel :ViewModel() {
    private val  dummyLiveData= MutableLiveData<MyDataResponse>()

    suspend fun getMydata() {
       MyRepository.getData()
     }

}
