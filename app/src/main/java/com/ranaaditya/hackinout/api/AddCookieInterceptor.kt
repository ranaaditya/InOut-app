package com.ranaaditya.hackinout.api

import android.content.Context
import android.preference.PreferenceManager
import com.ranaaditya.hackinout.api.ApiUtils.PREF_COOKIES
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AddCookieInterceptor(context: Context): Interceptor {

    private var ctx: Context = context
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder: Request.Builder = chain.request().newBuilder()

        var preferences: HashSet<String> = PreferenceManager.getDefaultSharedPreferences(ctx)
            .getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>

        var original = chain.request()

        if (original.url.toString().contains("distributor")) {
            for (cookie in preferences) {
                builder.addHeader("Cookie", cookie)
            }
        }

        return chain.proceed(builder.build())
    }

}