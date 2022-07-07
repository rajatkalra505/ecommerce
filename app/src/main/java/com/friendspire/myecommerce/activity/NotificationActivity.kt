package com.friendspire.myecommerce.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.friendspire.myecommerce.adapters.NotificationAdapter
import com.friendspire.myecommerce.data.NotificationData
import com.friendspire.myecommerce.databinding.ActivityNotificationBinding

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
        binding.recyclerNotification.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        nAdapter = NotificationAdapter(list, this, onItemClicked)

        binding.recyclerNotification.adapter = nAdapter
    }

    private val onItemClicked: (Int) -> Unit = { position ->

    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}
