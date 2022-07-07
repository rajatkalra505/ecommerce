package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.friendspire.myecommerce.EditProfileFragment
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityDashboardBinding
import com.friendspire.myecommerce.fragments.HomeFragment
import com.friendspire.myecommerce.fragments.OfferFragment
import com.friendspire.myecommerce.fragments.SettingFragment


class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeFragment = HomeFragment()
        val offerFragment = OfferFragment()
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
                R.id.nav_account -> {
                    setCurrentFragmentForSetting(EditProfileFragment())
                    binding.navigationDrawer.closeDrawers()
                }
                R.id.nav_settings -> {
                    setCurrentFragmentForSetting(SettingFragment())
                    binding.navigationDrawer.closeDrawers()
                }
                R.id.nav_logout -> {
                    showAlert()
                }
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
                    setCurrentFragmentForSetting(settingFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragmentForSetting(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer.id, fragment)
            addToBackStack(fragment.tag)
            commit()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.black))
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.black))
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        Log.i("mainActvitylobbby", "yesssssssssss")
    }

    fun getContainer(): FragmentContainerView {
        return binding.fragmentContainer
    }
}

