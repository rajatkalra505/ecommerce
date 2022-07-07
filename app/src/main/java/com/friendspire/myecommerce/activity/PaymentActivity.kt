package com.friendspire.myecommerce.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.databinding.PaymentActivityBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: PaymentActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PaymentActivityBinding.inflate(layoutInflater)
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

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, PaymentDetailActivity::class.java)
             getResult.launch(intent)
        }

        binding.btnCreditCard.setOnCheckedChangeListener { _, b ->
            if(b) {
                binding.btnCreditCard.isChecked = b
                binding.btnCash.isChecked = false
                binding.btnQr.isChecked = false
                binding.btnWallet.isChecked = false
            }
        }

        binding.btnCash.setOnCheckedChangeListener { _, b ->

            if (b) {
                binding.btnCash.isChecked = b
                binding.btnCreditCard.isChecked = false
                binding.btnQr.isChecked = false
                binding.btnWallet.isChecked = false
            }
        }

        binding.btnQr.setOnCheckedChangeListener { _, b ->
            if(b){
            binding.btnQr.isChecked = b
            binding.btnCreditCard.isChecked = false
            binding.btnCash.isChecked = false
            binding.btnWallet.isChecked = false
        }}
        binding.btnWallet.setOnCheckedChangeListener { _, b ->
            if(b){
            binding.btnWallet.isChecked = b
            binding.btnCreditCard.isChecked = false
            binding.btnCash.isChecked = false
            binding.btnQr.isChecked = false
        }
        }
    }

}

