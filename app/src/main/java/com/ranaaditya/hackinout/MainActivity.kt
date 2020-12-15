package com.ranaaditya.hackinout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ranaaditya.hackinout.api.ApiUtils
import com.ranaaditya.hackinout.api.LoginRequest
import com.ranaaditya.hackinout.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = LoginRequest("9500588159", "P@\$\$w0rd")

        //makeUserLogin(user)

    }


    fun makeUserLogin(loginRequest: LoginRequest) = lifecycleScope.launch {
        val httpsinterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = ApiUtils.provideOkHttpClient(httpsinterceptor)

        val gson = ApiUtils.provideGson()

        val retrofit = ApiUtils.provideRetrofit(client, gson)

        val response = withContext(Dispatchers.IO) {
            retrofit.login(loginRequest)
        }

        withContext(Dispatchers.Main) {
            if (response.sucess) {
                Log.d("LOGIN SUCCESSFUL", response.toString())
            } else {
                Log.d("LOGIN FAILED", response.toString())
            }

        }

    }
}