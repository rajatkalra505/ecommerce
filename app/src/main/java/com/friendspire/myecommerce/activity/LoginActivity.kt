package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.databinding.ActivityLoginBinding
import com.friendspire.myecommerce.utils.Utils

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
        binding.textEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    binding.clearEmail.visibility = View.GONE
                } else {
                    binding.clearEmail.visibility = View.VISIBLE

                }
            }
        })
        binding.textPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    binding.clearPassword.visibility = View.GONE
                } else {
                    binding.clearPassword.visibility = View.VISIBLE

                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            launchLobby()
        }
        binding.layoutSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    private fun launchLobby() {
        if (validate()) {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Entered Email or Password is wrong", Toast.LENGTH_SHORT).show()

        }
    }

    private fun validate(): Boolean {
        return !binding.textEmail.text.isNullOrEmpty()
                && !binding.textPassword.text.isNullOrEmpty()
                && Utils.isEmailValid(binding.textEmail.text.toString())

    }

}