package com.myecommerce.api

import com.myecommerce.data.MyDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("/photos")
     fun getDummyData(): Call<List<MyDataResponse>>
}