package com.tencent.compose1.ui.components


import android.content.Context
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*






@Composable
fun MyScreenContent() {
    val context = LocalContext.current
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val layoutParams = remember { WindowManager.LayoutParams().apply {
        type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        width = 300
        height = 300
        x = 100
        y = 100
    } }

}


//fun requestOverlayPermission(context: Context) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        if (!Settings.canDrawOverlays(context)) {
//            val intent = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
//            context.startActivity(intent)
//        }
//    }
//}