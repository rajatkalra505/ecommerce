package com.friendspire.myecommerce.repository

import androidx.lifecycle.MutableLiveData
import com.friendspire.myecommerce.api.RetrofitHelper
import com.friendspire.myecommerce.api.WebService
import com.friendspire.myecommerce.data.MyDataResponse

object MyRepository {

    val retrofitHelper=RetrofitHelper.retrofitHelper

    suspend fun getData()
    {

        //retrofitHelper.getDummyData().enqueue()

    }
}