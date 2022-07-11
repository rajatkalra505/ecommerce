package com.myecommerce.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myecommerce.data.NotificationData
import com.myecommerce.databinding.ItemAdapterNotificationBinding


class NotificationAdapter(
   private var mList: List<NotificationData?>?,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemAdapterNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        mList?.get(position)?.let { holder.bindItems(it) }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class MyHolder(private val myItemBinding: ItemAdapterNotificationBinding) :
        RecyclerView.ViewHolder(myItemBinding.root) {
        init {
            myItemBinding.root.setOnClickListener {
                onItemClicked.invoke(bindingAdapterPosition)
            }
        }
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindItems(data: NotificationData) {
            myItemBinding.txtTittle.text = data.title
        }
    }
}