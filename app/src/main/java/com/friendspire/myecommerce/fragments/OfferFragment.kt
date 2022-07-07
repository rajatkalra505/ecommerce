package com.friendspire.myecommerce.fragments

import android.os.Bundle
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
        val textView = TextView(context)
        textView.text = "        dynamic text view one"
        val textView2 = TextView(context)
        textView2.text = "        dynamic text view two"
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        textView2.layoutParams = params

        binding.rootLayout.addView(textView)
        binding.rootLayout.addView(textView2)
        textView.gravity = Gravity.CENTER
        textView2.gravity = Gravity.CENTER
    }

}