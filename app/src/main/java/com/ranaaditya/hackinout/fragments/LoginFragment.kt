package com.ranaaditya.hackinout.fragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ranaaditya.hackinout.R
import com.ranaaditya.hackinout.api.ApiUtils
import com.ranaaditya.hackinout.api.LoginRequest
import com.ranaaditya.hackinout.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        activity?.actionBar?.hide()

        val animDrawable = binding.loginFragmentRootLayout.background as AnimationDrawable

        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()


        binding.signupButtonInLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()

            findNavController().navigate(action)

        }

        binding.loginbutton.setOnClickListener {
            val SucessAction = LoginFragmentDirections.actionLoginFragmentToStatusFragment()
            val ErrorAction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()

            val ph = binding.loginphonenumber.toString()
            val  paswd = binding.loginwebmailpassword.toString()

            val loginResponse = LoginRequest(ph, paswd)

            findNavController().navigate(SucessAction)
            //makeUserLogin(loginResponse, activity as Context, SucessAction, ErrorAction)
        }
    }

    fun makeUserLogin(loginRequest: LoginRequest, ctx: Context, sucessaction: NavDirections, erroraction: NavDirections) = lifecycleScope.launch {
        val httpsinterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = ApiUtils.provideOkHttpClient(httpsinterceptor, ctx)

        val gson = ApiUtils.provideGson()

        val retrofit = ApiUtils.provideRetrofit(client, gson)

        val response = withContext(Dispatchers.IO) {
            retrofit.login(loginRequest)
        }

        withContext(Dispatchers.Main) {
            Log.d("is success", response.sucess.toString())
            Log.d("EXCLUSIVE", response.sucess.toString())

            if (response.sucess) {

                findNavController().navigate(sucessaction)
                Log.d("LOGIN SUCCESSFUL", response.toString())

            } else {
                findNavController().navigate(erroraction)
                Log.d("LOGIN FAILED", response.toString())
            }
        }
    }
}