package com.friendspire.myecommerce

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.adapters.SliderAdapter
import com.friendspire.myecommerce.databinding.FragmentMobileBinding
import com.friendspire.myecommerce.utils.Utils

class FullScreenSlider(
    images: List<Int>?
) : Fragment() {
    private lateinit var binding: FragmentMobileBinding

    private var mPosition: Int? = null
    private var mList: List<Int>? = null

    init {
        mList = images
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPosition = arguments?.getInt(Utils.IMAGE_POSITION) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMobileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSlideradapter()
        setListners()
        setPreviousScreenPositionForFullScreenPager()
    }

    private fun setListners() {
        binding.imageClose.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setSlideradapter() {
        val adapter =
            mList?.let { images ->
                SliderAdapter(images, onClick)
            }
        binding.viewPager.apply {
            this.adapter = adapter
        }
        
    }

    private fun setPreviousScreenPositionForFullScreenPager() {
        binding.viewPager.postDelayed(Runnable {
            binding.viewPager.setCurrentItem(mPosition ?: 0, true)
        }, 200)
    }

    private val onClick: (Int) -> Unit = { position ->

    }

}

