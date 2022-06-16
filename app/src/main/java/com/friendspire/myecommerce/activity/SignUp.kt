package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListners()

    }

    private fun setClickListners() {
        binding.layoutSignUp.setOnClickListener {
            launchLobby()
        }
    }

    private fun launchLobby() {
        if (!validate())
        {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this, "Please Enter all fields correctly", Toast.LENGTH_SHORT).show()

        }
    }
    private fun validate(): Boolean {
        return !binding.textEmail.text.isNullOrEmpty()
                && !binding.textPassword.text.isNullOrEmpty()
                && !binding.textFname.text.isNullOrEmpty()
                && !binding.textLname.text.isNullOrEmpty()
                && isEmailValid(binding.textEmail.text.toString())

    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


}
