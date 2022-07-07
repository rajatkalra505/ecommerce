package com.friendspire.myecommerce.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.data.MyDataResponse
import com.friendspire.myecommerce.databinding.MyItemBinding


class MyAdapter(
    var mList: MutableList<MyDataResponse?>?,
    var mContext: Context,
    private val onItemClicked :(Int)->Unit
) : RecyclerView.Adapter<MyAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = MyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindItems(mList?.get(position))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }
    inner class MyHolder(private val myItemBinding: MyItemBinding) : RecyclerView.ViewHolder(myItemBinding.root) {
      init {
          myItemBinding.root.setOnClickListener {
              onItemClicked?.invoke(bindingAdapterPosition)
          }
      }
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindItems(data:MyDataResponse?){
            myItemBinding.myId.text = data?.title
            Glide.with(myItemBinding.myImage)
                .load(data?.url+".png")
                //.load(data?.url)
                .placeholder(R.drawable.placeholder)
                .into(myItemBinding.myImage)
            Log.d("123123", "${data?.url}")
        }
    }
}