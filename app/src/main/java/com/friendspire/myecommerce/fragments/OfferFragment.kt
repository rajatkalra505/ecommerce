package com.friendspire.myecommerce.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.friendspire.myecommerce.OfferAdapter
import com.friendspire.myecommerce.data.NotificationData
import com.friendspire.myecommerce.databinding.FragmentOfferBinding


class OfferFragment : Fragment() {
    private lateinit var binding: FragmentOfferBinding
    private var mAdapter: OfferAdapter? = null
    private var list = ArrayList<NotificationData>()


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
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", true))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", true))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", true))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", false))
        list.add(NotificationData("Grab Dream Deals at Just Rs.1", true))
        setAdapter()
    }

    private fun setAdapter() {
        binding.offerRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //GridLayoutManager(context, 2)
        mAdapter = context?.let { OfferAdapter(list, it, onItemClicked) }

        binding.offerRecycler.adapter = mAdapter
    }

    private fun initViews() {
        binding.timer.apply {
            isCountDown = true
            base = SystemClock.elapsedRealtime() + 2000000
            start()
        }

    }

    private val onItemClicked: (Int) -> Unit = { position ->

    }

}