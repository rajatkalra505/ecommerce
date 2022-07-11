package com.myecommerce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myecommerce.data.OfferData
import com.myecommerce.databinding.ItemOfferBinding


class OfferAdapter(
    var mList: List<OfferData?>?,
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


        fun bindItems(data: OfferData) {
            myItemBinding.title.text = data.title
            myItemBinding.off.text = data.discount
            data.photoUrl?.let { myItemBinding.imgCategory.setImageResource(it) }

        }
    }
}