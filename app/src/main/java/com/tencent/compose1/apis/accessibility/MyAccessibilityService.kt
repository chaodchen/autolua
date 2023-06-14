package com.tencent.compose1.apis.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService: AccessibilityService() {
    private val tag = "MyAccessibilityService"
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
        var eventType = event?.eventType
        var eventText = ""
        when (eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED->
                eventText = "TYPE_VIEW_CLICKED"
            AccessibilityEvent.TYPE_VIEW_LONG_CLICKED->
                eventText = "TYPE_VIEW_LONG_CLICKED"
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED->
                eventText = "TYPE_WINDOW_STATE_CHANGED"
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED->
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED"
            AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE->
                eventText = "CONTENT_CHANGE_TYPE_SUBTREE"
        }
        Log.d(tag, eventText)
        Log.d(tag, "==================END===================")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}