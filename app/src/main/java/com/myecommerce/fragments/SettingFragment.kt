package com.myecommerce.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.myecommerce.R
import com.myecommerce.activity.HelpActivity
import com.myecommerce.activity.LobbyActivity
import com.myecommerce.activity.LobbyActivity.Companion.text_view_header
import com.myecommerce.activity.NotificationActivity
import com.myecommerce.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.visibility == View.VISIBLE) {
                    binding.webView.visibility = View.GONE
                    text_view_header?.text = getString(R.string.setting)
                } else {
                    val numberOfFragment = activity?.supportFragmentManager?.fragments?.size ?: 0
                    Log.e("numberOFFrags", numberOfFragment.toString())
                    if (numberOfFragment > 0) {
                        activity?.supportFragmentManager?.popBackStackImmediate()
                    } else {
                        activity?.finish()
                    }
                }
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListners()

    }


    private fun setListners() {
        binding.btnCash.setOnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            intent.putExtra("help", "help")
            getResult.launch(intent)
        }
        binding.btnHelp.setOnClickListener {
            val intent = Intent(context, HelpActivity::class.java)
            intent.putExtra("help", "help")
            getResult.launch(intent)
        }
        binding.btnAbout.setOnClickListener {
            val intent = Intent(context, HelpActivity::class.java)
            intent.putExtra("help", "about")
            getResult.launch(intent)
        }
        binding.btnWallet.setOnClickListener {
            text_view_header?.text = getString(R.string.privacy_and_policy)
            binding.webView.visibility = View.VISIBLE
            binding.webView.loadUrl("https://www.termsfeed.com/live/b506f00c-22ca-4910-b820-e9d8eacec9ac")
        }
        binding.Account.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                if (activity is LobbyActivity) {
                    val fragmentContainerView = (activity as LobbyActivity).getContainer()
                    val fragment = EditProfileFragment()
                    add(fragmentContainerView.id, fragment)
                    addToBackStack(fragment.tag)
                    commit()
                }


            }
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { }

}