package com.tencent.compose1.tool

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.accessibility.AccessibilityManager


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

fun requestAccessibilityPermission(context: Context) {
    // 获取 AccessibilityManager 实例
    val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    // 检查无障碍权限是否已经授权
    if (!accessibilityManager.isEnabled) {
        // 跳转到系统的无障碍权限管理界面
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}