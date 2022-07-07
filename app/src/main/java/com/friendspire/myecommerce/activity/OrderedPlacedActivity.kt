package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityOrderedPlacedBinding
import kotlinx.coroutines.*

class OrderedPlacedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderedPlacedBinding
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderedPlacedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        Glide.with(this)
            .load("https://freepngimg.com/thumb/emoji/65057-emoticon-signal-smiley-thumb-emoji-free-frame.png")
            .thumbnail(
                Glide.with(this).load(
                    R.drawable.placeholder
                )
            ).into(binding.ok)

        /*Handler().postDelayed({
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "jygf", Toast.LENGTH_SHORT).show()
        }, 3000)*/

        uiScope.launch {
            delay(3000)
            finishAffinity()
            val intent = Intent(this@OrderedPlacedActivity, LobbyActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
    }
}
