package com.myecommerce.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.myecommerce.fragments.FurnitureFragment


class MyTabAdapter(
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
                return FurnitureFragment()
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
