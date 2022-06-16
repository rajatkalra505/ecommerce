package com.friendspire.myecommerce.api

import com.friendspire.myecommerce.data.MyDataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET("/photos")
   suspend fun getDummyData(): Call<MyDataResponse>
}