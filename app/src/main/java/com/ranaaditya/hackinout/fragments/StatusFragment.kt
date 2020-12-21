package com.ranaaditya.hackinout.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.ranaaditya.hackinout.R
import com.ranaaditya.hackinout.databinding.FragmentStatusBinding
import com.ranaaditya.hackinout.utils.AppUtils

class StatusFragment : Fragment(R.layout.fragment_status) {

    var index = 0
    var moodtextindex = 0

    lateinit var binding: FragmentStatusBinding

    var handler = Handler()

    private lateinit var runnableCode: Runnable

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStatusBinding.bind(view)

        binding.moodImage.visibility = View.INVISIBLE

        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val animDrawable = binding.statusFragmentRootLayout.background as AnimationDrawable

        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()

        runnableCode = object : Runnable {

            @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
            override fun run() {


                Log.d("USER", "LOGGING")

                binding.textView.visibility = View.GONE
                binding.moodImage.visibility = View.VISIBLE
                index %= 8
                moodtextindex %= 8

                Log.d("USER MOOD", activity?.getDrawable(AppUtils.getUserMood(index)).toString())

                // fuck android dev !
                //binding.moodImage.setImageDrawable(activity!!.getDrawable(AppUtils.getUserMood(index)))
                binding.moodImage.background = activity?.getDrawable(AppUtils.getUserMood(index))

                binding.moodtext.text = "Hey rana your Mood is " + AppUtils.getMoodName(moodtextindex) + " !"
                index++
                moodtextindex++

                handler.postDelayed(this, 5000)
            }
        }

        handler.post(runnableCode)
    }

    override fun onResume() {
        super.onResume()
        handler.post(runnableCode)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnableCode)
    }
}