package com.tencent.compose1.ui.components

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tencent.compose1.apis.accessibility.MyAccessibilityService
import com.tencent.compose1.appContext


@SuppressLint("ServiceCast")
@Composable
fun AccessibilityServiceComponent() {
    val tag = "AccessibilityServiceComponent"
    if (isServiceON(appContext, MyAccessibilityService::class.java.name))
    {
        Log.d(tag, "无障碍权限已开启")
        Column() {
            Text(text = "无障碍权限已开启")
        }
    } else {
        Log.d(tag, "无障碍权限未开启")

        Column() {
            Text(text = "无障碍权限未开启")
        }
    }
}


fun isServiceON(context: Context, className: String?): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServices = activityManager.getRunningServices(100)
    if (runningServices.size < 0) {
        return false
    }
    for (i in runningServices.indices) {
        val service = runningServices[i].service
        if (service.className.contains(className!!)) {
            return true
        }
    }
    return false
}