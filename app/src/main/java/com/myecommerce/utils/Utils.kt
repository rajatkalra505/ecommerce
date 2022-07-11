package com.myecommerce.utils

import android.app.Activity
import android.widget.Toast
import com.myecommerce.data.MyDataResponse
import java.util.*
import java.util.regex.Pattern


object Utils {
    const val ITEM_ID = "ITEM_ID"
    const val ITEM_TITLE = "ITEM_TITLE"
    const val ITEM_URL = "ITEM_URL"
    const val ITEM_PRICE = "ITEM_PRICE"
    const val RESULT_CODE_LIST_UPDATE = 110
    const val IMAGE_POSITION = "IMAGE_POSITION"

    fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    var product: MutableList<MyDataResponse>? = ArrayList()

    fun getRandomNumber(): Int {
        val random = arrayListOf(30, 35, 40, 45, 50)
        return random.random()
    }

    fun showToast(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}