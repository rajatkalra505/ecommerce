package com.friendspire.myecommerce.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.databinding.ActivityAdressBinding

class AdressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListners()
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
            }
        }

    private fun setListners() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.checkOut.setOnClickListener {
            if (Validate()) {
                //showAlert()
                val intent = Intent(this, PaymentActivity::class.java)
                getResult.launch(intent)
            }
            else
                Toast.makeText(this, "Please Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Validate(): Boolean {
        return !binding.fullName.text.isNullOrEmpty()
                && !binding.adressline1.text.isNullOrEmpty()
                && !binding.zipCode.text.isNullOrEmpty()
                && !binding.townCity.text.isNullOrEmpty()
                && !binding.state.text.isNullOrEmpty()
                && binding.zipCode.text.toString().trim().length == 6


    }

}

