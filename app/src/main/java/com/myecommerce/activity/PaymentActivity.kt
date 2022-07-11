package com.myecommerce.activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.myecommerce.R
import com.myecommerce.databinding.PaymentActivityBinding

class PaymentActivity : AppCompatActivity(), View.OnClickListener {

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
        ) {}

    private fun setListners() {
        binding.imgBack.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)


        //payment options check handle
        binding.btnCreditCard.setOnCheckedChangeListener { _, b ->
            if (b) {
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
            if (b) {
                binding.btnQr.isChecked = b
                binding.btnCreditCard.isChecked = false
                binding.btnCash.isChecked = false
                binding.btnWallet.isChecked = false
            }
        }
        binding.btnWallet.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding.btnWallet.isChecked = b
                binding.btnCreditCard.isChecked = false
                binding.btnCash.isChecked = false
                binding.btnQr.isChecked = false
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            //back press buton
            R.id.img_back->{
                onBackPressed()

            }
            //go to payment detail screen
            R.id.btn_continue->{
                val intent = Intent(this, PaymentDetailActivity::class.java)
                getResult.launch(intent)
            }
        }
    }
}

