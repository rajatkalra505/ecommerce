package com.friendspire.myecommerce.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val msg = intent.getStringExtra("help")
        if (msg == "help") {
            binding.titleLabel.text = getString(R.string.Help)
            binding.label.text = getString(R.string.please_read)
        } else {
            binding.titleLabel.text = getString(R.string.About)
            binding.label.text = getString(R.string.please_read_about)

        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }
}