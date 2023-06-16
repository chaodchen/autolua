package com.tencent.compose1.tool

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import androidx.compose.ui.res.integerArrayResource
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.tencent.compose1.apis.accessibility.MyAccessibilityService
import com.tencent.compose1.appContext


fun isServiceON(context: Context): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServices = activityManager.getRunningServices(100)
    if (runningServices.size < 0) {
        return false
    }
    for (i in runningServices.indices) {
        val service = runningServices[i].service
        if (service.className.contains(MyAccessibilityService::class.java.name!!)) {
            return true
        }
    }
    return false
}

fun requestAccessibilityPermission(context: Context) {
    // 获取 AccessibilityManager 实例
    val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    // 检查无障碍权限是否已经授权 右侧方法判定有误  !accessibilityManager.isEnabled
    if (!isServiceON(context)) {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}


fun isFloatingServiceON(context: Context): Boolean {
    return !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context))
}

fun requestFloatingServicePermission(context: Context) {
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    intent.data = Uri.parse("package:${appContext.packageName}")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
