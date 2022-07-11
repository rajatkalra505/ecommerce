package com.friendspire.myecommerce.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
        binding.clearFname.setOnClickListener { binding.textFname.text?.clear() }
        binding.clearLname.setOnClickListener { binding.textLname.text?.clear() }
        binding.clearEmail.setOnClickListener { binding.textEmail.text?.clear() }
        binding.clearPassword.setOnClickListener { binding.textPassword.text?.clear() }

        binding.textFname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    binding.clearFname.visibility = View.GONE
                } else {
                    binding.clearFname.visibility = View.VISIBLE
                }
            }
        })
        binding.textLname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    binding.clearLname.visibility = View.GONE
                } else {
                    binding.clearLname.visibility = View.VISIBLE
                }
            }
        })
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

        binding.textTermsAndCondition.setOnClickListener {
            binding.webView.visibility = View.VISIBLE
            binding.myToolbar.visibility = View.VISIBLE
            binding.webView.loadUrl("https://www.termsfeed.com/live/b506f00c-22ca-4910-b820-e9d8eacec9ac")
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else if (binding.webView.visibility == View.VISIBLE) {
            binding.webView.visibility = View.GONE
            binding.myToolbar.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    private fun launchLobby() {
        if (!validate()) {
            savetoPrefrencs()
            val intent = Intent(this, LobbyActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please Enter all fields correctly", Toast.LENGTH_SHORT).show()

        }
    }

    private fun savetoPrefrencs() {
        val sharedPreferences = getSharedPreferences("My_pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("fnamekey", binding.textFname.text.toString())
        editor.putString("lnamekey", binding.textLname.text.toString())
        editor.putString("emailkey", binding.textEmail.text.toString())
        editor.apply()
        editor.commit()
    }

    private fun validate(): Boolean {
        return !binding.textEmail.text.isNullOrEmpty()
                && !binding.textPassword.text.isNullOrEmpty()
                && !binding.textFname.text.isNullOrEmpty()
                && !binding.textLname.text.isNullOrEmpty()
                && isEmailValid(binding.textEmail.text.toString())
                && binding.materialCheckBox.isChecked
    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


}
