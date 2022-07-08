package com.friendspire.myecommerce.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val msg = intent.getStringExtra("help")
        if (msg == "help")
        {
            binding.titleLabel.text="Help"
            binding.label.text="Please Read For Help"
        }
        else
        {
            binding.titleLabel.text="About"
            binding.label.text="Please Read About"

        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }
}