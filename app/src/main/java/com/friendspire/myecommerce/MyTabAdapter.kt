package com.friendspire.myecommerce

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MyTabAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    private var totalTabs: Int
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = FurnitureFragment()
                fragment.setCount(0)
                return fragment
            }
            1 -> {
                val fragment = FurnitureFragment()
                fragment.setCount(1)
                return fragment
            }
            2 -> {
                val fragment = FurnitureFragment()
                fragment.setCount(2)
                return fragment
            }
            else -> {
                val fragment = FurnitureFragment()
                
                return fragment
                //getItem(position)
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
