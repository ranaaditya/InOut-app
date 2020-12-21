package com.ranaaditya.hackinout.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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

        binding.signupButtonInLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()

            findNavController().navigate(action)

        }

        binding.loginbutton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToStatusFragment()

            findNavController().navigate(action)
        }
    }

    fun makeUserLogin(loginRequest: LoginRequest, ctx: Context) = lifecycleScope.launch {
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
            if (response.sucess) {
                Log.d("LOGIN SUCCESSFUL", response.toString())
            } else {
                Log.d("LOGIN FAILED", response.toString())
            }
        }
    }
}