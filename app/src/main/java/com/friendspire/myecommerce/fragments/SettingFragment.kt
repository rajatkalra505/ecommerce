package com.friendspire.myecommerce.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.friendspire.myecommerce.EditProfileFragment
import com.friendspire.myecommerce.activity.HelpActivity
import com.friendspire.myecommerce.activity.LobbyActivity
import com.friendspire.myecommerce.activity.NotificationActivity
import com.friendspire.myecommerce.databinding.FragmentSettingBinding

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
                if (binding.webView.visibility == View.VISIBLE)
                    binding.webView.visibility = View.GONE
                else {
                    val numberOfFragment = activity?.supportFragmentManager?.fragments?.size ?: 0
                    Log.e("numberOFFrags", numberOfFragment.toString())
                    if (numberOfFragment > 0) {
                        activity?.supportFragmentManager?.popBackStackImmediate()
                    } else {
                        activity?.finish()
                    }
                }

                Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
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
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
            }

        }

}