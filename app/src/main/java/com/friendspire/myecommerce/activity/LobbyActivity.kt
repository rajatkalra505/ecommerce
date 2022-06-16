package com.friendspire.myecommerce.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.fragments.HomeFragment
import com.friendspire.myecommerce.fragments.OfferFragment
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.fragments.SettingFragment
import com.friendspire.myecommerce.databinding.ActivityDashboardBinding


class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeFragment= HomeFragment()
        val offerFragment= OfferFragment()
val settingFragment = SettingFragment()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                binding.navigationDrawer,
                R.string.nav_open,
                R.string.nav_close
            )

        binding.navigationDrawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navDrawerDashboard.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_account -> {}
                R.id.nav_settings -> {}
                R.id.nav_logout -> {}

            }
            true
        }
        setCurrentFragment(homeFragment)
        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.page_2 -> {
                    setCurrentFragment(offerFragment)
                    true
                }
                R.id.page_3 -> {
                    setCurrentFragment(settingFragment)
                    true
                }
                else -> false
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id,fragment)
            commit()
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

