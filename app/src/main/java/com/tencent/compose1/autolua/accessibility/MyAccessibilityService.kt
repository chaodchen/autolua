package com.tencent.compose1.autolua.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class MyAccessibilityService: AccessibilityService() {
    private val myUtils = MyAccessibilityUtils(this)
    private val tag = "MyAccessibilityService"
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val node = myUtils.findAccessibilityNodeByText("微信")
        if (node != null) {
//            myUtils.clickAccessibilityNode(node)
            Log.d(tag, "找到了")
        } else {
            Log.d(tag, "没找到")
        }
    }

    override fun onInterrupt() {

    }

}