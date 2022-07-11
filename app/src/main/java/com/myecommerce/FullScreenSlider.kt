package com.myecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.myecommerce.adapters.SliderAdapter
import com.myecommerce.databinding.FragmentMobileBinding
import com.myecommerce.utils.Utils
import kotlin.math.abs

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
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer((ViewPager2.PageTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85F + r * .15F
        }))
        binding.viewPager.setPageTransformer(compositePageTransformer)
        
    }

    private fun setPreviousScreenPositionForFullScreenPager() {
        binding.viewPager.postDelayed(Runnable {
            binding.viewPager.setCurrentItem(mPosition ?: 0, true)
        }, 200)
    }

    private val onClick: (Int) -> Unit = { position ->

    }

}

