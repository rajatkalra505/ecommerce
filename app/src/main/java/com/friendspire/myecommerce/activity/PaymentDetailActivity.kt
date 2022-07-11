package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityPaymentDetailBinding
import com.friendspire.myecommerce.utils.Utils


class PaymentDetailActivity : AppCompatActivity() {
    private var currentYear = Utils.getCurrentYear()
    private var nextYear = currentYear + 10
    private var current = ""
    private lateinit var binding: ActivityPaymentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListners()

        val monthArray = resources.getStringArray(R.array.myMonth)
        val spinnerAdapter = object : ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            monthArray
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
        }
        binding.month.adapter = spinnerAdapter

        val years: MutableList<String>
        years = ArrayList()
        years.add("YY")
        for (i in currentYear..nextYear) {
            years.add(i.toString())
        }
        val spinnerAdapterYear = object : ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            years
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
        }
        binding.year.adapter = spinnerAdapterYear


    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}

    private fun validate(): Boolean {

        return !binding.edittextCardNumber.text.isNullOrEmpty()
                && !binding.month.isSelected
                && !binding.year.isSelected
                && !binding.cvv.text.isNullOrEmpty()
                && binding.cvv.text.toString().length == 3
                && binding.edittextCardNumber.text.toString().length > 18
                && !binding.edittextCardHolderName.text.isNullOrEmpty()
                && binding.month.selectedItem != "MM"
                && binding.year.selectedItem != "YY"
    }

    private fun setListners() {
        binding.btnContinue.setOnClickListener {
            if (validate()) {
                val intent = Intent(this, OrderedPlacedActivity::class.java)
                getResult.launch(intent)
            } else
                Toast.makeText(this, "Please Enter all details", Toast.LENGTH_SHORT).show()
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.labelCvv.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("The CVV/CVC code (Card Verification Value/Code) is located on the back of your credit/debit card on the right side of the white signature strip; it is always the last 3 digits in case of VISA and MasterCard.")
            builder.show()
        }

        binding.edittextCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("121212", "afterTextChanged: ${s?.length}")
                if (s.toString() != current) {
                    val userInput = s.toString().replace(Regex("[^\\d]"), "")
                    if (userInput.length == 4) {
                        binding.imgVisa.visibility = View.VISIBLE
                    }
                    if (userInput.isEmpty()) {
                        binding.imgVisa.visibility = View.GONE

                    }
                    if (userInput.length <= 16) {
                        current = userInput.chunked(4).joinToString(" ")
                        s?.filters = arrayOfNulls<InputFilter>(0)
                    }
                    s?.replace(0, s.length, current, 0, current.length)
                }
            }

        }

        )
    }
}