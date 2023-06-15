package com.tencent.compose1.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.tencent.compose1.apis.accessibility.MyAccessibilityService
import com.tencent.compose1.apis.autolua.MyAutoLua
import com.tencent.compose1.appContext
import com.tencent.compose1.tool.FileTool
import com.tencent.compose1.tool.isServiceON
import com.tencent.compose1.tool.requestAccessibilityPermission
import com.tencent.compose1.ui.components.FileInfoItem
import com.tencent.compose1.ui.components.MultiplePermissions

import com.tencent.compose1.viewmodel.HomeViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(),
               onNavigateToSetting: () -> Unit) {

    val tag = "HomeScreen"
    val lua = MyAutoLua()

    // 进这个页面的时候获取列表数据
    LaunchedEffect(key1 = UInt) {
        homeViewModel.fetchFileList()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Home")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            onNavigateToSetting()
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { it ->
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            MultiplePermissions()

            if (isServiceON(appContext, MyAccessibilityService::class.java.name)) {
                LazyColumn() {
                    items(homeViewModel.fileList) { item ->
                        FileInfoItem(fileInfo = item, onItemClick = { file ->
                            Log.d(tag,  "fileName: ${file.name}")
                            var fileContent = FileTool.readFromAssets(appContext,
                                "scripts/${file.name}")
                            var ret = lua.LdoString(fileContent)
                            Log.d(tag, "luaResult: $ret")
                        })
                    }
                }
            } else {
                TextButton(onClick = {
                    requestAccessibilityPermission(appContext)

                }) {
                    Text(text = "点击授予无障碍权限")

                }
            }
        }
    }
}

fun fibonacci(n: Int): Int {
    return if (n == 0 || n == 1) {
        n
    } else {
        fibonacci(n-1) + fibonacci(n-2)
    }
}