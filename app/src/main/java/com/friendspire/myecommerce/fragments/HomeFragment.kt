package com.friendspire.myecommerce.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityDashboardBinding
import com.friendspire.myecommerce.databinding.FragmentHomeBinding
import com.friendspire.myecommerce.repository.MyModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
     var mViewModel: MyModel? = null
    private lateinit var binding: FragmentHomeBinding

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this)[MyModel::class.java]
        ioScope.launch {
        }
//mViewModel?.getMydata()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return  binding.root
            }
}