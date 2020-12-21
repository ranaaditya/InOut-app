package com.ranaaditya.hackinout.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranaaditya.hackinout.R
import com.ranaaditya.hackinout.databinding.FragmentStatusBinding

class StatusFragment: Fragment(R.layout.fragment_status) {

    lateinit var binding: FragmentStatusBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStatusBinding.bind(view)

        //activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val animDrawable = binding.statusFragmentRootLayout.background as AnimationDrawable

        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()

    }
}