package com.friendspire.myecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.adapters.SliderAdapter
import com.friendspire.myecommerce.databinding.FragmentMobileBinding

class FullScreenSlider(position: Int, images: List<Int>?) : Fragment() {
    private lateinit var binding: FragmentMobileBinding

    private var mPosition: Int? = null
    private var mList: List<Int>? = null

    init {
        mPosition = position
        mList = images
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
        binding.viewPager.setCurrentItem(mPosition ?: 0, true)
    }

    private val onClick: (Int) -> Unit = { position ->

    }

}

