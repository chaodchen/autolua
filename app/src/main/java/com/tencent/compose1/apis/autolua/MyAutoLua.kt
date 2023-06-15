package com.tencent.compose1.apis.autolua

import android.util.Log
import org.keplerproject.luajava.LuaStateFactory

class MyAutoLua {
    private val lua = LuaStateFactory.newLuaState()
    init {
        lua.openLibs()
        lua.pushJavaObject(Log::class.java)
        lua.setGlobal("Log")
    }

    fun LdoFile(filePath: String) {
        lua.LdoFile(filePath)
    }

    fun LdoString(luaCode: String): Int {
        return lua.LdoString(luaCode)
    }

    fun dumpStack(): String? {
        return lua.dumpStack()
    }

}