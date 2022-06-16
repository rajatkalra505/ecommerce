package com.friendspire.myecommerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.friendspire.myecommerce.databinding.ActivityLoginBinding
import com.friendspire.myecommerce.utils.Utils
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setClickListeners()
    }
    private fun init() {
    }

    private fun setClickListeners() {
        binding.clearEmail.setOnClickListener { binding.textEmail.text?.clear() }
        binding.clearPassword.setOnClickListener { binding.textPassword.text?.clear() }
        binding.buttonLogin.setOnClickListener {
            launchLobby()
        }
        binding.layoutSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    private fun launchLobby() {
        if (validate())
        {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this, "Entered Email or Password is wrong", Toast.LENGTH_SHORT).show()

        }    }

    private fun validate(): Boolean {
        return !binding.textEmail.text.isNullOrEmpty()
                && !binding.textPassword.text.isNullOrEmpty()
                && Utils.isEmailValid(binding.textEmail.text.toString())

    }

}