package com.friendspire.myecommerce.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.adapters.MyTabAdapter
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.activity.CartActivity
import com.friendspire.myecommerce.activity.LobbyActivity.Companion.text_view_header
import com.friendspire.myecommerce.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setClickListners()
        text_view_header?.text = getString(R.string.my_ecommerce)
    }

    private fun initViews() {
        binding.tabLayout.apply {
            addTab(this.newTab().setText("Furniture"))
            addTab(this.newTab().setText("Beauty"))
            addTab(this.newTab().setText("Mobile"))
            addTab(this.newTab().setText("Electronics"))
            addTab(this.newTab().setText("Laptops"))
            addTab(this.newTab().setText("Acessories"))
            tabGravity = TabLayout.GRAVITY_FILL
        }
        val mTabAdapter = context?.let { ctx ->
            MyTabAdapter(
                childFragmentManager,
                binding.tabLayout.tabCount
            )
        }
        binding.viewPager.adapter = mTabAdapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.viewPager.currentItem = 0
        binding.tabLayout.setScrollPosition(0, 0f, true)
        val tabs = binding.tabLayout.getTabAt(0)
        tabs?.view?.setBackgroundColor(Color.BLUE)
    }


    @SuppressLint("RestrictedApi")
    private fun setClickListners() {
        binding.goToCart.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    tab.view.setBackgroundColor(Color.BLUE)

                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundColor(Color.TRANSPARENT)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundColor(Color.BLUE)
            }
        })

    }


}
