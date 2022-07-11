package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityOrderedPlacedBinding
import com.friendspire.myecommerce.utils.Utils
import kotlinx.coroutines.*

class OrderedPlacedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderedPlacedBinding
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderedPlacedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        binding.ok.setImageResource(R.drawable.smile)
        uiScope.launch {
            delay(3000)
            finishAffinity()
            Utils.product?.clear()
            val intent = Intent(this@OrderedPlacedActivity, LobbyActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
    }
}
