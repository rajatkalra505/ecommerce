package com.friendspire.myecommerce.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.databinding.FragmentOfferBinding


class OfferFragment : Fragment() {
    private lateinit var binding: FragmentOfferBinding


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

    }

    private fun initViews() {
        binding.timer.apply {
            isCountDown=true
            base = SystemClock.elapsedRealtime() + 2000000
            start()
        }

    }

}