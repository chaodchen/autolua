package com.tencent.compose1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tencent.compose1.entity.Todo

class HomeViewModel : ViewModel(){
    // 脚本列表
    var list by mutableStateOf<List<Todo>>(listOf())

    fun fetchList() {
        list = listOf(
            Todo("11111", "500", "nihao", "js"),
            Todo("22222", "100", "ceshi", "lua")
        )
    }
}