package com.myecommerce.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myecommerce.R
import com.myecommerce.databinding.ActivityOrderedPlacedBinding
import com.myecommerce.utils.Utils
import kotlinx.coroutines.*

class OrderedPlacedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderedPlacedBinding
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderedPlacedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        val orderId = generateOrderId()
        binding.orderId.text = "Order Id $orderId"
        binding.ok.setImageResource(R.drawable.smile)

        //clear cart and move to dashboard
        uiScope.launch {
            delay(3000)
            finishAffinity()
            Utils.product?.clear()
            val intent = Intent(this@OrderedPlacedActivity, LobbyActivity::class.java)
            startActivity(intent)
        }
    }


    //generate random order id
    private fun generateOrderId(): Int {
        return (100000000..999999999).random()
    }

    override fun onBackPressed() {
        //remove super call to avoid back press
    }
}
