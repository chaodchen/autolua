package com.tencent.compose1.ui.components


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import com.tencent.compose1.R
import com.tencent.compose1.appContext


@SuppressLint("MissingInflatedId")
fun myFloatingWindow(onClick: View.OnClickListener) {
    val params = WindowManager.LayoutParams().apply {
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }
        flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        format = PixelFormat.TRANSLUCENT
        gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
        x = 0
        y = 500
    }


    val inflater = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.floating_window, null)

    val windowManager = appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.addView(view, params)
    view.findViewById<ImageButton>(R.id.play).setOnClickListener(onClick)
}
