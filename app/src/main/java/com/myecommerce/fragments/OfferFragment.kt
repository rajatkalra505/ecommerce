package com.myecommerce.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.myecommerce.adapters.OfferAdapter
import com.myecommerce.R
import com.myecommerce.data.OfferData
import com.myecommerce.databinding.FragmentOfferBinding


class OfferFragment : Fragment() {
    private lateinit var binding: FragmentOfferBinding
    private var mAdapter: OfferAdapter? = null
    private var list = ArrayList<OfferData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfferBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setList()
    }

    private fun setList() {
        list.add(OfferData("Car and Bike Accessories", "Extra 8% Off",R.drawable.car))
        list.add(OfferData("Mob Sets,Cleaning gloves ", "Extra 10% Off",R.drawable.mob))
        list.add(OfferData("Containers ,Bottles & more", "Upto 50% Off",R.drawable.bottle))
        list.add(OfferData("Home Decoratives", "Extra 20% Off",R.drawable.decors))
        list.add(OfferData("Premium Electronics", "Extra 15% Off",R.drawable.speaker))
        list.add(OfferData("Computers and Laptops", "Extra 5% Off",R.drawable.computer))
        list.add(OfferData("Latest Mobiles", "Upto 45% Off",R.drawable.mobile))

        setAdapter()
    }

    private fun setAdapter() {
        binding.offerRecycler.layoutManager = GridLayoutManager(context, 2)
        mAdapter = context?.let { OfferAdapter(list, onItemClicked) }

        binding.offerRecycler.adapter = mAdapter
    }

    private fun initViews() {
        binding.timer.apply {
            isCountDown = true
            base = SystemClock.elapsedRealtime() + 2000000
            start()
        }

    }

    private val onItemClicked: (Int) -> Unit = { _ ->

    }

}