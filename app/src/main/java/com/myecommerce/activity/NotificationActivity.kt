package com.myecommerce.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myecommerce.R
import com.myecommerce.adapters.NotificationAdapter
import com.myecommerce.data.NotificationData
import com.myecommerce.databinding.ActivityNotificationBinding


//notification activity
class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private var list = ArrayList<NotificationData>()
    private var nAdapter: NotificationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setList()
        setListeners()

    }

    private fun setList() {
        for (i in 1..10)
            list.add(NotificationData(getString(R.string.grab_deales), true))

        setAdapter()
    }


    //set adapter for notification
    private fun setAdapter() {
        binding.recyclerNotification.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        nAdapter = NotificationAdapter(list, onItemClicked)
        binding.recyclerNotification.adapter = nAdapter
    }

    private val onItemClicked: (Int) -> Unit = { _ ->

    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}
