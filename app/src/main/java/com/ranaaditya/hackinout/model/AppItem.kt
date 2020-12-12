package com.ranaaditya.hackinout.model

import java.util.*

class AppItem {

    var appName: String = ""
    var appPackageName: String = ""
    var appEventTime: Long = 0
    var appUsageTime: Long = 0
    var appCount: Int = 0
    var appEventType: Int = 0
    var appCanOpen: Boolean = false
    var appMobile: Long = 0
    var isSystemApp: Boolean = false

    constructor() {}

    constructor(
        name: String,
        pkgname: String,
        eventime: Long,
        eventype: Int,
        usagetime: Long,
        count: Int,
        canopen: Boolean,
        mobile: Long,
        systemapp: Boolean
    ) {
        this.appName = name
        this.appPackageName = pkgname
        this.appEventTime = eventime
        this.appEventType = eventype
        this.appUsageTime = usagetime
        this.appCount = count
        this.appCanOpen = canopen
        this.appMobile = mobile
        this.isSystemApp = systemapp
    }

    override fun toString(): String {
        return String.format(
            Locale.getDefault(),
            "name:%s package_name:%s time:%d total:%d type:%d system:%b count:%d",
            appName, appPackageName, appEventTime, appUsageTime, appEventType, isSystemApp, appCount
        );
    }

}