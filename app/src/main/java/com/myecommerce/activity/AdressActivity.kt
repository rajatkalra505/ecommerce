package com.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.myecommerce.R
import com.myecommerce.databinding.ActivityAdressBinding

class AdressActivity : AppCompatActivity(), View.OnClickListener {

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
        }

    //set click listners
    private fun setListners() {
        binding.imgBack.setOnClickListener(this)
        binding.checkOut.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.img_back -> {
                onBackPressed()
            }

            //go to choose payment method screen
            R.id.check_out -> {
                if (isNameNotEmpty()) {
                    if (isAddressNotEmpty()) {
                        if (isZipNotEmpty()) {
                            if (isZipNotValid()) {
                                if (isTownNotEmpty()) {
                                    if (isStateNotEmpty()) {
                                        val intent = Intent(this, PaymentActivity::class.java)
                                        getResult.launch(intent)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Please Enter state",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Please Enter Town or city",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Please Enter valid Zip address",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        } else {
                            Toast.makeText(this, "Please Enter Zip address", Toast.LENGTH_SHORT)
                                .show()

                        }
                    } else {
                        Toast.makeText(this, "Please Enter address", Toast.LENGTH_SHORT).show()

                    }
                } else {
                    Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isNameNotEmpty(): Boolean {
        return !binding.fullName.text.isNullOrEmpty()
    }

    private fun isAddressNotEmpty(): Boolean {
        return !binding.adresslineone.text.isNullOrEmpty()
    }

    private fun isZipNotEmpty(): Boolean {
        return !binding.zipCode.text.isNullOrEmpty()
    }

    private fun isZipNotValid(): Boolean {
        return binding.zipCode.text.toString().trim().length == 6
    }

    private fun isTownNotEmpty(): Boolean {
        return !binding.townCity.text.isNullOrEmpty()
    }

    private fun isStateNotEmpty(): Boolean {
        return !binding.state.text.isNullOrEmpty()
    }


}

