package com.myecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.myecommerce.R
import kotlinx.android.synthetic.main.view_pager_item.view.*

class SliderAdapter(private val images: List<Int>,private val onItemClicked :(Int)->Unit
) :
    RecyclerView.Adapter<SliderAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.indicator_image?.setOnClickListener {
                onItemClicked.invoke(bindingAdapterPosition)
            }
        }
        val imageView: ImageView = itemView.findViewById(R.id.indicator_image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.ViewPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.imageView.setImageResource(curImage)
    }
    override fun getItemCount(): Int {
        return images.size
    }
}