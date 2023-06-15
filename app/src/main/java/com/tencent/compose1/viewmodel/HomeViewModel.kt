package com.tencent.compose1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tencent.compose1.appContext
import com.tencent.compose1.entity.FileInfo
import com.tencent.compose1.tool.FileTool.Companion.listAssetsFiles

class HomeViewModel : ViewModel(){
    // 脚本列表
    var fileList by mutableStateOf<List<FileInfo>>(listOf())


    fun fetchFileList(){
        fileList = listAssetsFiles(appContext, "scripts")
    }
}

