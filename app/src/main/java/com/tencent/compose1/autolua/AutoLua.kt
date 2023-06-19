package com.tencent.compose1.autolua

import android.util.Log
import com.tencent.compose1.autolua.accessibility.MyAccessibilityService
import com.tencent.compose1.autolua.accessibility.MyAccessibilityUtils
import org.keplerproject.luajava.LuaState
import org.keplerproject.luajava.LuaStateFactory


class AutoLua {
    private val lua = LuaStateFactory.newLuaState()
    init {
        lua.openLibs()
        lua.pushJavaObject(Log::class.java)
        lua.setGlobal("Log")
        lua.pushJavaObject(MyAccessibilityUtils(MyAccessibilityService()))
        lua.setGlobal("Auto")
    }

//    constructor() {
//
//    }

    fun doString(str: String) {
        lua.LdoString(str)
    }
}