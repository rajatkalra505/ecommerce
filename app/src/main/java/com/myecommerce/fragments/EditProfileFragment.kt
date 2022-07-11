package com.myecommerce.fragments

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.myecommerce.databinding.FragmentEditProfileBinding


class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValue()
        setClickListners()
    }

    private fun initValue() {
        val preferences = context?.getSharedPreferences("My_pref", 0)
        val fname = preferences?.getString("fnamekey", "")
        val lname = preferences?.getString("lnamekey", "")
        val email = preferences?.getString("emailkey", "")
        binding.textName.setText(fname)
        binding.textAge.setText(lname)
        binding.textPhone.setText(email)


    }

    private fun setClickListners() {
        binding.checkBoxMale.setOnClickListener {
            binding.checkBoxFemale.isChecked = false
        }
        binding.checkBoxFemale.setOnClickListener {
            binding.checkBoxMale.isChecked = false
        }
        binding.btnUploadImage.setOnClickListener {
            binding.camera.performClick()
        }
        binding.camera.setOnClickListener {
            onClickRequestPermission()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                getResult.launch(cameraIntent)
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    private fun onClickRequestPermission() {
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            getResult.launch(cameraIntent)
        } else {
            if (activity?.let {
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        it,
                        Manifest.permission.CAMERA
                    )
                } == false) {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            } else {
                openSettingPermissionForApp()
            }


        }
    }

    private fun openSettingPermissionForApp() {
        try {
            val packageName = activity?.packageName
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)

        } catch (e: ActivityNotFoundException) {
            val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
            startActivity(intent)
        }
    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.imageProfile.setImageBitmap(it?.data?.extras?.get("data") as Bitmap)
            }

        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val numberOfFragment = activity?.supportFragmentManager?.fragments?.size ?: 0
                Log.e("numberOFFrags", numberOfFragment.toString())
                if (numberOfFragment > 0) {
                    activity?.supportFragmentManager?.popBackStackImmediate()
                } else {
                    activity?.finish()
                }
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}
