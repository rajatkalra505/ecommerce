package com.friendspire.myecommerce.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.data.MyDataResponse
import com.friendspire.myecommerce.databinding.ItemCartLayoutBinding
import com.friendspire.myecommerce.utils.Utils


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
                if (bindingAdapterPosition >= 0 && bindingAdapterPosition < (productList?.size ?: 0)) {
                    onProductAddClick(bindingAdapterPosition)
                }
            }
            myItemBinding.imgDeleteItem.setOnClickListener {
                if (bindingAdapterPosition >= 0 && bindingAdapterPosition < (productList?.size ?: 0)) {
                    onProductDeleteClick(bindingAdapterPosition)
                }
            }

        }

        @SuppressLint("SetTextI18n")
        fun bindItems(data: MyDataResponse?) {
            myItemBinding.textQuantityTemp.text = data?.countSelected.toString()
            myItemBinding.price.text =
                "₹" + ((data?.price?.let { data.countSelected?.times(it) }).toString())
            Glide.with(myItemBinding.myImage)
                .load(data?.url + ".png")
                .placeholder(R.drawable.placeholder)
                .into(myItemBinding.myImage)
        }
    }
}