package com.tencent.compose1.apis.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
class MyAccessibilityService : AccessibilityService() {
    private var rootNode: AccessibilityNodeInfo? = null
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        rootNode = rootInActiveWindow
    }
    override fun onInterrupt() {}
//    fun findViewByViewId(viewId: String): AccessibilityNodeInfo? {
//        if (rootNode == null) {
//            return null
//        }
//        val nodes = rootNode?.findAccessibilityNodeInfosByViewId(viewId)
//        if (nodes.isNullOrEmpty()) {
//            return null
//        }
//        return nodes[0]
//    }
}