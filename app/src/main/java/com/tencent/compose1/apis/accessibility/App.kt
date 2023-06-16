package com.tencent.compose1.apis.accessibility
import android.content.Intent
import android.util.Log
import com.tencent.compose1.appContext


object App {
    private const val tag: String = "App"
    fun launchApp(packageName: String) {
        val pm = appContext.packageManager
        val intent = pm.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            appContext.startActivity(intent)
        } else {
            // 应用程序不存在，可以在这里处理异常情况
            Log.d(tag, "应用不存在")
        }
    }
}


