package com.ranaaditya.hackinout.App

import android.app.Application
import com.ranaaditya.hackinout.db.AppDataBase

class App: Application() {

    companion object {
        @JvmStatic
        private var app: Application? = null

        @JvmStatic
        fun  app() = app!!

        @JvmStatic
        private var database: AppDataBase? = null

        @JvmStatic
        fun getAppDatabase() = database!!
    }

    override fun onCreate() {

        super.onCreate()
        app = this
        database = AppDataBase(this)

    }
}