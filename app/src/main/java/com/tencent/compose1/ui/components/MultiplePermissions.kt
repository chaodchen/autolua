package com.tencent.compose1.ui.components

import android.Manifest
import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.io.File
import kotlin.system.exitProcess
import com.tencent.compose1.appContext

const val tag = "MultiplePermissions"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissions() {
    val openDialog = remember { mutableStateOf(true) }

    val multiplePermissions = rememberMultiplePermissionsState(listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ))
    if (multiplePermissions.allPermissionsGranted) {
        Log.d(tag, "所有权限已获取")
        // 创建本地脚本目录
        createDirectory("luaScript")
        createDirectory("luaExample")

        openDialog.value = false
    } else {
        Log.d(tag, "开始申请权限")
        if (openDialog.value) {
            AlertDialog(onDismissRequest = { /*TODO*/ },
                title = { Text(text = "温馨提示") }, text = { Text(text = "该软件需要一些权限。") },
                confirmButton = {
                    // 确认
                    TextButton(onClick = {
                        multiplePermissions.launchMultiplePermissionRequest()
                    }) {
                        Text(text = "确认")
                    }
                }, dismissButton = {
                    // 退出
                    TextButton(onClick = {
                        exitProcess(0)
                    }) {
                        Text(text = "退出")
                    }
                }
            )
        }
    }
}


fun createDirectory(folderName: String) {
    val folder = File(appContext.getExternalFilesDir(null), folderName)
    if (!folder.exists()) {
        folder.mkdirs()
    }
}