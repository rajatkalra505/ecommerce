package com.friendspire.myecommerce.repository

import com.friendspire.myecommerce.api.RetrofitHelper
import com.friendspire.myecommerce.data.MyDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyRepository {

    private val retrofitHelper = RetrofitHelper.retrofitHelper

    fun getData(
        successHandler: (List<MyDataResponse>) -> Unit,
        failureHandler: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        retrofitHelper?.getDummyData()?.enqueue(object : Callback<List<MyDataResponse>> {
            override fun onResponse(
                call: Call<List<MyDataResponse>>,
                response: Response<List<MyDataResponse>>
            ) {
                response.body()?.let {
                    successHandler(it)
                }
                response.errorBody()?.let {
                    failureHandler(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<MyDataResponse>>, t: Throwable) {
                onFailure(t)
            }
        })


    }
}