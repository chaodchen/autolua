package com.tencent.compose1

import android.app.Application
import android.content.Context


class MyApplication : Application() {
    companion object{
        lateinit var instance: Application
    }
    init {
        instance = this
    }
}

val appContext: Context = MyApplication.instance.applicationContext
