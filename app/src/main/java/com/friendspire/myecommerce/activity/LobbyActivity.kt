package com.friendspire.myecommerce.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.friendspire.myecommerce.fragments.EditProfileFragment
import com.friendspire.myecommerce.R
import com.friendspire.myecommerce.databinding.ActivityDashboardBinding
import com.friendspire.myecommerce.fragments.HomeFragment
import com.friendspire.myecommerce.fragments.OfferFragment
import com.friendspire.myecommerce.fragments.SettingFragment


class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    companion object {
        @SuppressLint("StaticFieldLeak")
        var text_view_header: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        text_view_header = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
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
                    text_view_header?.text = getString(R.string.profile)
                    setCurrentFragmentForSetting(EditProfileFragment())
                    binding.navigationDrawer.closeDrawers()
                }
                R.id.nav_settings -> {
                    text_view_header?.text = getString(R.string.setting)
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
                    text_view_header?.text = getString(R.string.my_ecommerce)
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.page_2 -> {
                    text_view_header?.text = getString(R.string.offer)
                    setCurrentFragment(offerFragment)
                    true
                }
                R.id.page_3 -> {
                    text_view_header?.text = getString(R.string.setting)
                    setCurrentFragmentForSetting(settingFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragmentForSetting(fragment: Fragment) {
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction().apply {
                add(binding.fragmentContainer.id, fragment)
                addToBackStack(fragment.tag)
                commit()
            }
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure want to Logout")
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
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.black))
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(this,R.color.black))
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

    fun getContainer(): FragmentContainerView {
        return binding.fragmentContainer
    }
}

