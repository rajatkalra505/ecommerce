package com.myecommerce.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myecommerce.R
import com.myecommerce.data.MyDataResponse
import com.myecommerce.databinding.ItemCartLayoutBinding


class CartAdapter(
    private val productList: MutableList<MyDataResponse>?,
    private val onProductAddClick: (Int) -> Unit,
    private val onProductDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemCartLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindItems(productList?.get(position))
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    inner class MyHolder(private val myItemBinding: ItemCartLayoutBinding) :
        RecyclerView.ViewHolder(myItemBinding.root) {
        init {
            myItemBinding.imgAddItem.setOnClickListener {
                if (bindingAdapterPosition >= 0 && bindingAdapterPosition < (productList?.size
                        ?: 0)
                ) {
                    onProductAddClick(bindingAdapterPosition)
                }
            }
            myItemBinding.imgDeleteItem.setOnClickListener {
                if (bindingAdapterPosition >= 0 && bindingAdapterPosition < (productList?.size
                        ?: 0)
                ) {
                    onProductDeleteClick(bindingAdapterPosition)
                }
            }

        }

        @SuppressLint("SetTextI18n")
        fun bindItems(data: MyDataResponse?) {
            if (data?.countSelected.toString().toInt() > 0) {
                myItemBinding.mainCard.visibility=View.VISIBLE
                myItemBinding.textQuantityTemp.text = data?.countSelected.toString()
                myItemBinding.price.text =
                    "₹" + ((data?.price?.let { data.countSelected?.times(it) }).toString())
                Glide.with(myItemBinding.myImage)
                    .load(data?.url + ".png")
                    .placeholder(R.drawable.placeholder)
                    .into(myItemBinding.myImage)
            }
            else
            {
                myItemBinding.mainCard.visibility=View.GONE
            }
        }
    }
}