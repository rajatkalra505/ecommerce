package com.myecommerce.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.myecommerce.FullScreenSlider
import com.myecommerce.R
import com.myecommerce.adapters.SliderAdapter
import com.myecommerce.data.MyDataResponse
import com.myecommerce.databinding.ActivityDetailBinding
import com.myecommerce.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.abs


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var id: Int? = null
    private var quantity: Int = 0
    private var title: String? = null
    private var image: String? = null
    private var price: Int = 0
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    private var images: List<Int>? = null
    private var dataItem: MyDataResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        setData()
        setClickListners()

    }

    @SuppressLint("SetTextI18n")
    private fun setClickListners() {

        //share to other apps
        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.textsend))
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        //add item
        binding.imgAddItem.setOnClickListener {
            ioScope.launch {
                quantity++
                dataItem?.countSelected = quantity
                uiScope.launch {
                    binding.price.text = "₹" + ((quantity * price).toString())
                    binding.textQuantity.text = quantity.toString()
                }
            }
        }

        //delete item
        binding.imgDeleteItem.setOnClickListener {
            ioScope.launch {
                if (quantity > 0) {
                    quantity--
                    dataItem?.countSelected = quantity
                    uiScope.launch {
                        binding.price.text = "₹" + ((quantity * price).toString())
                        binding.textQuantity.text = quantity.toString()
                    }
                }
            }
        }


        //all product list to cart
        binding.buttonAddToCart.setOnClickListener {
            Utils.product?.let { list ->
                if (list.isNotEmpty()) {
                    var index = -1
                    for (i in 0 until list.size) {
                        if (this@DetailActivity.id != list[i].id) {
                            index = i
                        } else {
                            list[i].countSelected = binding.textQuantity.text.toString().toInt()
                            index = -1
                            break
                        }
                    }
                    if (index > -1) {
                        dataItem?.countSelected = binding.textQuantity.text.toString().toInt()
                        if (binding.textQuantity.text.toString().toInt() > 0)
                            dataItem?.let { it1 -> list.add(it1) }
                        else {
                        }
                    } else {
                    }
                } else {
                    if (binding.textQuantity.text.toString().toInt() > 0)
                        dataItem?.let { it1 -> list.add(it1) }
                    else {
                    }
                }
            }
            val intent = Intent(this, CartActivity::class.java)
            getResult.launch(intent)
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                this.finish()
            }
            if (it.resultCode == Utils.RESULT_CODE_LIST_UPDATE) {
                setList()
            }
        }

    private fun initValues() {
        val arguments = intent.extras?.getBundle("dataBundle")
        id = arguments?.getInt("ITEM_ID")
        title = arguments?.getString("ITEM_TITLE")
        image = arguments?.getString("ITEM_URL")
        price = arguments?.getInt(Utils.ITEM_PRICE) ?: 0
        setList()

        dataItem = MyDataResponse().apply {
            id = this@DetailActivity.id
            price = this@DetailActivity.price
            title = this@DetailActivity.title
            url = this@DetailActivity.image
            countSelected = quantity
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setList() {
        Utils.product?.let { list ->
            if (list.isNotEmpty()) {

                for (item in list) {
                    if (this@DetailActivity.id == item.id) {
                        binding.textQuantity.text = item.countSelected.toString()
                        binding.price.text = item.price.toString()
                        item.countSelected?.let { quantity = it }
                        binding.price.text = (quantity * price).toString()
                        binding.pricePerItem.text = "₹$price"
                    } else {
                        binding.textQuantity.text = quantity.toString()
                        binding.price.text = (quantity * price).toString()
                        binding.pricePerItem.text = "₹$price"

                    }

                }
            } else {
                binding.textQuantity.text = "0"
                binding.price.text = "0"
                binding.pricePerItem.text = "₹$price"

            }
        }
    }

    private fun setData() {
        binding.title.text = resources.getString(R.string.dummy_text)
        images = listOf(
            R.drawable.ecommerceimage,
            R.drawable.background_detail,
            R.drawable.ecommerceimage,
            R.drawable.background_detail,
        )
        val adapter =
            images?.let { images ->
                SliderAdapter(images, onClick)
            }

        //sliding images animation
        binding.myImageCard.adapter = adapter
        binding.myImageCard.apply {
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
        binding.myImageCard.setPageTransformer(compositePageTransformer)
        binding.myImageCard.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeColor()
            }
        })
    }


    //set image to full when clicked
    private val onClick: (Int) -> Unit = { position ->
        val bundle = Bundle().apply {
            putInt(Utils.IMAGE_POSITION, position)
        }
        val fragment = FullScreenSlider(images)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer.id, fragment)
            addToBackStack(null)
            commit()
        }
    }


    //photo slider indicator color change
    private fun changeColor() {
        when (binding.myImageCard.currentItem) {
            0 -> {
                binding.indicatorOne.setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                binding.indicatorTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))
                binding.indicatorThree.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))

            }
            1 -> {
                binding.indicatorOne.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))
                binding.indicatorTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                binding.indicatorThree.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))
            }
            2 -> {
                binding.indicatorOne.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))
                binding.indicatorTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.grey))
                binding.indicatorThree.setBackgroundColor(applicationContext.resources.getColor(R.color.black))
            }
        }
    }
}