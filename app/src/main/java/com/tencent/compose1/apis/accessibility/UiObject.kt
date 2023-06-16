package com.tencent.compose1.apis.accessibility


import android.os.Build
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat
import java.security.AllPermission
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.R)
open class UiObject(info: AccessibilityNodeInfo): AccessibilityNodeInfo(info){
    init {

    }
}