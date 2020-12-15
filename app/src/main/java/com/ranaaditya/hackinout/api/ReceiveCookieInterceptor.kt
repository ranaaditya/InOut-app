package com.ranaaditya.hackinout.api

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class ReceiveCookieInterceptor(context: Context): Interceptor {

      private var ctx: Context = context

    override fun intercept(chain: Interceptor.Chain): Response {

        var originalResponse = chain.proceed(chain.request())

        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            var cookies: HashSet<String> = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>

            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header!!)
                Log.d("HEADER- RECEIVE", header.toString())
            }

            val memes = PreferenceManager.getDefaultSharedPreferences(ctx).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }


}