package com.ranaaditya.hackinout.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.ranaaditya.hackinout.R

object AppUtils {

    const val DAY: Long = 86400 * 1000

    val MoodMap = mapOf(
        1 to R.drawable.anxious,
        2 to R.drawable.bored,
        3 to R.drawable.bored,
        4 to R.drawable.envious,
        5 to R.drawable.gloomy,
        6 to R.drawable.inferior,
        7 to R.drawable.upset,
        8 to R.drawable.stressed
    )

    fun isSYstemApplication(pkgm: PackageManager, pkgname: String): Boolean {
        var isSystemApp: Boolean = false

        try {
            var appInfo: ApplicationInfo? = pkgm.getApplicationInfo(pkgname, 0)
            if (appInfo != null) {
                isSystemApp =
                    (appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) || (appInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP != 0)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return isSystemApp
    }

    fun parsePackageName(pkgm: PackageManager, name: String): String {

        var appInfo: ApplicationInfo? = null

        try {
            appInfo = pkgm.getApplicationInfo(name, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            appInfo = null
            e.printStackTrace()
        }

        if (appInfo == null) return name

        return pkgm.getApplicationLabel(appInfo).toString()
    }

    fun getPackageIcon(ctx: Context, packageName: String): Drawable {

        var pkgm: PackageManager = ctx.packageManager

        try {
            return pkgm.getApplicationIcon(packageName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ctx.resources.getDrawable(R.drawable.ic_launcher_background)
    }


    fun isInstalled(pkgm: PackageManager, packageName: String): Boolean {
        var appInfo: ApplicationInfo? = null

        try {
            appInfo = pkgm.getApplicationInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return appInfo != null
    }

    fun isOpenableApp(pkgm: PackageManager, packageName: String): Boolean {
        return pkgm.getLaunchIntentForPackage(packageName) != null
    }

    fun getAppUID(pkgm: PackageManager, packageName: String): Int {

        var appInfo: ApplicationInfo

        try {
            appInfo = pkgm.getApplicationInfo(packageName, 0)
            return appInfo.uid
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return 0
    }

//    fun getMood(i: Int): Int? {
//        var arr = mapOf<Int, Int>()
//        arr[1] = R.drawable.anxious
//        arr[2] = R.drawable.bored
//        arr[3] = R.drawable.envious
//        arr[4] = R.drawable.gloomy
//        arr[5] = R.drawable.stressed
//        arr[6] = R.drawable.tired
//        arr[7] = R.drawable.upset
//        arr[8] = R.drawable.inferior
//
//        return arr.get(i)
//    }

    fun getUserMood(i: Int) = MoodMap.getOrDefault(i, R.drawable.bored)

}