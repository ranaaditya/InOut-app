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

        val user = LoginRequest("", "")

        //makeUserLogin(user)

    }


    fun makeUserLogin(loginRequest: LoginRequest) = lifecycleScope.launch {
        val httpsinterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = ApiUtils.provideOkHttpClient(httpsinterceptor, this@MainActivity)

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