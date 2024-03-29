package com.myecommerce.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myecommerce.adapters.CartAdapter
import com.myecommerce.databinding.ActivityCartBinding
import com.myecommerce.utils.Utils


class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private var adapter: CartAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        getTotalPrice()
        setClickListners()

    }

    //click lisners
    private fun setClickListners() {
        binding.checkOut.setOnClickListener {
            val intent = Intent(this, AdressActivity::class.java)
            getResult.launch(intent)
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}

    //gross price for all items
    @SuppressLint("SetTextI18n")
    private fun getTotalPrice() {
        var total = 0
        Utils.product?.let { list ->
            if (list.isNotEmpty()) {
                for (i in 0 until list.size) {
                    list[i].price?.let { itemPrice ->
                        list[i].countSelected?.let { itemQuantity ->
                            total += (itemQuantity * itemPrice)
                        }
                    }
                }
            }
        }
        if (total == 0) {
            binding.emptyCart.visibility = View.VISIBLE
            binding.checkOut.isEnabled = false
        } else {
            binding.checkOut.isEnabled = true
            binding.emptyCart.visibility = View.GONE

        }
        binding.totalPrice.text = "₹ $total"
    }

    private fun setAdapter() {
        binding.cartRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CartAdapter(Utils.product, handleAddItemData, handleDeleteItemData)
        binding.cartRecycler.adapter = adapter
    }

    //update cart when item added from cart
    private val handleAddItemData: (Int) -> Unit = { position ->
        val dataItem = Utils.product?.get(position)
        dataItem?.countSelected = dataItem?.countSelected?.plus(1)
        adapter?.notifyItemChanged(position)
        getTotalPrice()
        handleData()

    }

    //update cart when item deleted from cart
    private val handleDeleteItemData: (Int) -> Unit = { position ->
        val dataItem = Utils.product?.get(position)
        if ((dataItem?.countSelected ?: 0) > 1) {
            dataItem?.countSelected = dataItem?.countSelected?.minus(1)
            getTotalPrice()
            handleData()
            adapter?.notifyItemChanged(position)
        } else {
            dataItem?.countSelected = dataItem?.countSelected?.minus(1)
            Utils.product?.removeAt(position)
            getTotalPrice()
            handleData()
            adapter?.notifyItemRemoved(position)
        }
    }

    private fun handleData() {
        val intent = Intent()
        setResult(Utils.RESULT_CODE_LIST_UPDATE, intent)
    }


}
    
