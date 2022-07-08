package com.friendspire.myecommerce

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.friendspire.myecommerce.data.NotificationData
import com.friendspire.myecommerce.databinding.ItemOfferBinding


class OfferAdapter(
    var mList: List<NotificationData?>?,
    var mContext: Context,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<OfferAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        mList?.get(position)?.let { holder.bindItems(it) }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class MyHolder(private val myItemBinding: ItemOfferBinding) :
        RecyclerView.ViewHolder(myItemBinding.root) {
        init {
            myItemBinding.root.setOnClickListener {
                onItemClicked.invoke(bindingAdapterPosition)
            }
        }

        fun bindItems(data: NotificationData) {

        }
    }
}