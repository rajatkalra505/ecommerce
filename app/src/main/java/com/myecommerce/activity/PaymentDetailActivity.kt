package com.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myecommerce.R
import com.myecommerce.databinding.ActivityPaymentDetailBinding
import com.myecommerce.utils.Utils


class PaymentDetailActivity : AppCompatActivity(), View.OnClickListener {
    private var currentYear = Utils.getCurrentYear()
    private var nextYear = currentYear + 10
    private var current = ""
    private lateinit var binding: ActivityPaymentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListners()


        //spinner for month and year
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
        years.add(getString(R.string.yy))
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


    private fun setListners() {
        binding.btnContinue.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        binding.labelCvv.setOnClickListener(this)

        binding.edittextCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
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


    private fun isCardCorrect(): Boolean {
        return binding.edittextCardNumber.text.toString().length > 18
    }

    private fun isCardEmpty(): Boolean {
        return !binding.edittextCardNumber.text.isNullOrEmpty()
    }

    private fun isMonthSelected(): Boolean {
        return binding.month.selectedItem != getString(R.string.mm)
    }

    private fun isYearSelected(): Boolean {
        return binding.year.selectedItem != getString(R.string.yy)
    }

    private fun isCvvEmpty(): Boolean {
        return !binding.cvv.text.isNullOrEmpty()
    }

    private fun isCvvCorrect(): Boolean {
        return binding.cvv.text.toString().length == 3
    }

    private fun isHolderNameNotEmpty(): Boolean {
        return !binding.edittextCardHolderName.text.isNullOrEmpty()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            //back press buton
            R.id.img_back -> {
                onBackPressed()

            }
            //go to ordered placed screen
            R.id.btn_continue -> {
                if (isCardEmpty()) {
                    if (isCardCorrect()) {
                        if (isMonthSelected()) {
                            if (isYearSelected()) {
                                if (isCvvEmpty()) {
                                    if (isCvvCorrect()) {
                                        if (isHolderNameNotEmpty()) {
                                            val intent =
                                                Intent(this, OrderedPlacedActivity::class.java)
                                            getResult.launch(intent)
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Please Enter card Holder Name",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Please Enter correct Cvv",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                } else {
                                    Toast.makeText(this, "Please Enter Cvv", Toast.LENGTH_SHORT)
                                        .show()

                                }

                            } else {
                                Toast.makeText(this, "Please select Year", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        } else {
                            Toast.makeText(this, "Please select Month", Toast.LENGTH_SHORT).show()

                        }
                    } else {
                        Toast.makeText(this, "Please Enter correct card Number", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "Please Enter  card Number", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            //cvv info
            R.id.label_cvv -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.cvvinfo))
                builder.show()
            }
        }
    }
}