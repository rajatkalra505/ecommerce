package com.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myecommerce.databinding.ActivityLoginBinding
import com.myecommerce.utils.Utils

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
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

        //go to signup
        binding.layoutSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        binding.clearEmail.setOnClickListener { binding.textEmail.text?.clear() }
        binding.clearPassword.setOnClickListener { binding.textPassword.text?.clear() }

    }


    //launch lobby when login is success
    private fun launchLobby() {
        if (isMailNotEmpty()) {
            if (isMailValid()) {
                if (isPwdNotEmpty()) {
                    val intent = Intent(this, LobbyActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter valid Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isPwdNotEmpty(): Boolean {
        return !binding.textPassword.text.isNullOrEmpty()
    }

    private fun isMailValid(): Boolean {
        return Utils.isEmailValid(binding.textEmail.text.toString())
    }

    private fun isMailNotEmpty(): Boolean {
        return !binding.textEmail.text.isNullOrEmpty()
    }

}