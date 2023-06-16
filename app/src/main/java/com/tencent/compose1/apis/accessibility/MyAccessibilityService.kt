package com.tencent.compose1.apis.accessibility

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
class MyAccessibilityService : AccessibilityService() {
    private var rootNode: AccessibilityNodeInfo? = null
    private val tag: String = javaClass.simpleName
    override fun onServiceConnected() {
        Log.d(tag, "onServiceConnected")
        super.onServiceConnected()
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        监听全局事件
        Log.d(tag, "onAccessibilityEvent: $event")
//        rootNode = rootInActiveWindow
    }
    override fun onInterrupt() {
        // 服务中断时回调
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(tag, "onUnbind")

        return super.onUnbind(intent)
    }
}