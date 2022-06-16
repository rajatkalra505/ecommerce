package com.friendspire.myecommerce.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataResponse(

    @field:SerializedName("albumId")
    var albumId:Int?=null,

    @field:SerializedName("id")
    var id:Int?=null,

    @field:SerializedName("title")
    var title:String?=null,

    @field:SerializedName("url")
    var url:String?=null,

    @field:SerializedName("thumbnailUrl")
    var thumbnailUrl:String?=null,

):Parcelable
