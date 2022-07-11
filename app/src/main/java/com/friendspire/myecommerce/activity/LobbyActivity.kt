package com.friendspire.myecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
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
companion object{
    var text_view_header:TextView?=null
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
                    val textView: TextView? = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
                    text_view_header?.text="Profile"
                    setCurrentFragmentForSetting(EditProfileFragment())
                    binding.navigationDrawer.closeDrawers()
                }
                R.id.nav_settings -> {
                    val textView: TextView? = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
                    text_view_header?.text="Setting"
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
                    val textView: TextView? = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
                    text_view_header?.text="My Ecommerce"
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.page_2 -> {
                    val textView: TextView? = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
                    text_view_header?.text="Offer"
                    setCurrentFragment(offerFragment)
                    true
                }
                R.id.page_3 -> {
                    val textView: TextView? = supportActionBar?.customView?.findViewById(R.id.text_action_bar)
                    text_view_header?.text="Setting"
                    setCurrentFragmentForSetting(settingFragment)
                    true
                }
                else -> false
            }
        }
        setNavHeader()
    }

    private fun setNavHeader() {
//        supportActionBar?.title.
//        val navigationView = binding.navigationDrawer
//        val headerView = navigationView.getChildAt(0)
//        if (headerView is TextView) {
//            headerView.text = "My App"
//        }
    }

    private fun setCurrentFragmentForSetting(fragment: Fragment) {
        if (!fragment.isAdded){
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

    fun getContainer(): FragmentContainerView {
        return binding.fragmentContainer
    }
}

