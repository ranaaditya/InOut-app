package com.ranaaditya.hackinout.utils

import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.os.Process
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

object UsageStatsUtils {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getUsageStats(ctx: Context) {
        lateinit var topPackageName: String

        val usageStatsManager =
            ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var time: Long = System.currentTimeMillis()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -1)

        var usageStatList: List<UsageStats> = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_WEEKLY,
            calendar.timeInMillis,
            time
        )

        for (tmp in usageStatList) {
            Log.d("PACKAGE NAME", AppUtils.parsePackageName(ctx.packageManager, tmp.packageName))
        }

        //Log.d("USAGE STATS", usageStatList.toString())

        if (usageStatList.isNotEmpty()) {
            var sortedMap: SortedMap<Long, UsageStats> = TreeMap<Long, UsageStats>()

            for (usagestats in usageStatList) {
                sortedMap[usagestats.lastTimeUsed] = usagestats
            }

            if (sortedMap.isNotEmpty()) {
                topPackageName = sortedMap[sortedMap.lastKey()]!!.packageName
            }

            Log.d("TOP PACKAGE NAME", topPackageName)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun checkForAppOpsPermission(ctx: Context): Boolean {
        val appOps: AppOpsManager = ctx.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

        val mode: Int =
            appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), ctx.packageName)

        return mode == MODE_ALLOWED
    }
}