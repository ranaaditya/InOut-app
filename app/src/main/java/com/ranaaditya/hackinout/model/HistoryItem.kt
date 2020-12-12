package com.ranaaditya.hackinout.model

import java.util.*

class HistoryItem {

    var packageName: String = ""
    var appName: String = ""
    var date: String = ""
    var isSystemApp: Boolean = false
    var duration: Long = 0
    var timeStamp: Long = 0
    var mobileTraffic: Long = 0

    override fun toString(): String {
        return String.format(
            Locale.getDefault(),
            "%s %s %s %d %d %d %d",
            packageName,
            appName,
            date,
            isSystemApp,
            duration,
            timeStamp,
            mobileTraffic
        )
    }
}