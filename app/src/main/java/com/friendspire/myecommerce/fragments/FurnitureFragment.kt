package com.friendspire.myecommerce.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.activity.DetailActivity
import com.friendspire.myecommerce.adapters.MyAdapter
import com.friendspire.myecommerce.data.MyDataResponse
import com.friendspire.myecommerce.databinding.FragmentFurnitureBinding
import com.friendspire.myecommerce.repository.MyModel
import com.friendspire.myecommerce.utils.Utils
import java.util.*


class FurnitureFragment : Fragment() {

    var mViewModel: MyModel? = null
    private lateinit var binding: FragmentFurnitureBinding
    private var mList: MutableList<MyDataResponse?>? = null
    private var mTempList: MutableList<MyDataResponse?>? = null
    private var adapter: MyAdapter? = null
    private var frag_count: Int? = null
    var title: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[MyModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.dummyLiveData?.observe(this, Observer {
            it?.let { it1 ->
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
                for (item in it1) {
                    item.price = Utils.getRandomNumber()
                    item.countSelected = 0
                    mList?.add(item)
                    mTempList?.add(item)
                    item.title?.let { it2 -> title?.add(it2) }
                }
                adapter?.notifyItemRangeChanged(0, mList?.size ?: 0)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFurnitureBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    hitApi()
        initViews()
        setAdapter()
        setListners()
        val face: Typeface = Typeface.createFromAsset(context?.assets, "fonts/titania_regular.ttf")
        val searchText = binding.searchAction.findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView
        searchText.typeface = face
    }

    private fun hitApi() {
        val internet = context?.let { checkForInternet(it) }

        if (internet == true){
            mViewModel?.getMydata()
            binding.retry.visibility=View.GONE
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.visibility = View.VISIBLE}
        else
        {
            binding.retry.visibility=View.VISIBLE
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE

        }


    }

    @SuppressLint("RestrictedApi")
    private fun setListners() {

        binding.retry.setOnClickListener {
        hitApi()
        }

        binding.filterData.setOnClickListener {
            val popupMenu = context?.let { ctx -> PopupMenu(ctx, binding.filterData) }
            popupMenu?.inflate(R.menu.filter_menu)
            popupMenu?.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.option_1 -> {
                        activity?.let { Utils.showToast(it, "Clicked on High to Low") }
                    }
                    R.id.option_2 -> {
                        activity?.let { Utils.showToast(it, "Clicked on Low to High") }
                    }
                }
                true
            }
            val menuHelper = context?.let { ctx ->
                MenuPopupHelper(ctx, (popupMenu?.menu) as MenuBuilder, binding.filterData)
            }
            menuHelper?.setForceShowIcon(true)
            menuHelper?.show()
        }


        val closeButton: View? =
            binding.searchAction.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.searchAction.setQuery("", false)
            mTempList?.clear()
            mList?.let { list ->
                mTempList?.addAll(list)
            }
            binding.recyclerViewDummyData.visibility = View.VISIBLE
            binding.noResult.visibility = View.GONE
            adapter?.notifyItemRangeChanged(0, mTempList?.size ?: 0)
            val inputMethodManager: InputMethodManager =
                activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }




        binding.searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mTempList?.clear()
                val searchText = newText?.lowercase(Locale.getDefault())
                if (searchText?.isNotEmpty() == true) {
                    mList?.forEach {
                        if (it?.title?.lowercase(Locale.getDefault())
                                ?.contains(searchText) == true
                        ) {
                            mTempList?.add(it)
                        }

                    }
                    if (mTempList?.isEmpty() == true) {
                        binding.recyclerViewDummyData.visibility = View.GONE
                        binding.noResult.visibility = View.VISIBLE
                    } else {
                        binding.recyclerViewDummyData.visibility = View.VISIBLE
                        binding.noResult.visibility = View.GONE

                    }

                    adapter?.notifyItemRangeChanged(0, mTempList?.size ?: 0)
                } else {
                    mTempList?.clear()
                    mList?.let { list ->
                        mTempList?.addAll(list)
                    }
                    binding.recyclerViewDummyData.visibility = View.VISIBLE
                    binding.noResult.visibility = View.GONE
                    adapter?.notifyItemRangeChanged(0, mTempList?.size ?: 0)
                }
                return false
            }

        })
        binding.recyclerViewDummyData.setOnTouchListener { _, _ ->
            val inputMethodManager: InputMethodManager =
                activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
            false
        }
    }

    private fun setAdapter() {

        when (frag_count) {
            0 -> {
                binding.recyclerViewDummyData.layoutManager = GridLayoutManager(activity, 2)

            }
            1 -> {
                binding.recyclerViewDummyData.layoutManager = GridLayoutManager(activity, 3)

            }
            else -> {
                binding.recyclerViewDummyData.layoutManager = GridLayoutManager(activity, 2)

            }
        }
        adapter = activity?.let {
            MyAdapter(mTempList, onItemClicked)
        }

        binding.recyclerViewDummyData.adapter = adapter
    }

    private val onItemClicked: (Int) -> Unit = { position ->
        mList?.get(position)?.url
        val bundle = Bundle().apply {
            putInt(Utils.ITEM_ID, mList?.get(position)?.id ?: 0)
            putString(Utils.ITEM_TITLE, mList?.get(position)?.title ?: "")
            putString(Utils.ITEM_URL, mList?.get(position)?.url ?: "")
            putInt(Utils.ITEM_PRICE, mList?.get(position)?.price ?: 0)
        }
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("dataBundle", bundle)
        startActivity(intent)
        // Toast.makeText(activity, "$position", Toast.LENGTH_SHORT).show()

    }

    private fun initViews() {
        mList = ArrayList()
        title = ArrayList()
        mTempList = ArrayList()
    }

    fun setCount(i: Int) {
        frag_count = i
      //  Log.e("121212", "setCount: $frag_count")
        // Toast.makeText(context, "$frag_count", Toast.LENGTH_SHORT).show()
    }
    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}
