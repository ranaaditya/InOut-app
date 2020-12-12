package com.ranaaditya.hackinout.usagestats

import android.app.AppOpsManager
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.os.Process
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.ranaaditya.hackinout.model.AppItem

class UserDataManager {

    private fun containsItem(items: List<AppItem>, packageName: String): AppItem? {
        for (it in items) {
            if (it.appPackageName == packageName)
                return it
        }
        return null
    }

    private fun inIgnoreList(list: List<AppItem>, packageName: String): Boolean {
        for (it in list) {
            if (it.appPackageName == packageName)
                return true
        }

        return false
    }


    companion object {
        @JvmStatic
        fun requestDataUsagePermission(ctx: Context) {
            var intent = Intent(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            ctx.startActivity(intent)
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        @JvmStatic
        fun hasAppOpsPermission(ctx: Context): Boolean {

            var appOpsManager = ctx.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?

            if (appOpsManager != null) {
                var mode = appOpsManager.checkOpNoThrow(
                    OPSTR_GET_USAGE_STATS,
                    Process.myUid(),
                    ctx.packageName
                )
                return mode == AppOpsManager.MODE_ALLOWED
            }

            return false
        }

    }

}