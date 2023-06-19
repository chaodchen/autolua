package com.tencent.compose1.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.tencent.compose1.appContext
import com.tencent.compose1.autolua.AutoLua
import com.tencent.compose1.autolua.accessibility.MyAccessibilityService
import com.tencent.compose1.autolua.accessibility.MyAccessibilityUtils
import com.tencent.compose1.tool.FileTool
import com.tencent.compose1.tool.isAccessibilityServiceEnabled
import com.tencent.compose1.tool.isFloatingServiceON
import com.tencent.compose1.tool.requestAccessibilityPermission
import com.tencent.compose1.tool.requestFloatingServicePermission
import com.tencent.compose1.ui.components.FileInfoItem
import com.tencent.compose1.ui.components.MultiplePermissions
import com.tencent.compose1.ui.components.myFloatingWindow
import com.tencent.compose1.ui.nav.Destinations


import com.tencent.compose1.viewmodel.HomeViewModel
import kotlin.system.exitProcess


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel,
               nav: NavHostController
) {

    val tag = "HomeScreen"
    val lua = AutoLua()
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("日志", "设置", "关于", "退出")

    // 进这个页面的时候获取列表数据
    LaunchedEffect(key1 = UInt) {
        homeViewModel.fetchFileList()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Home")
        }, actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.MoreVert, null )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                items.forEachIndexed {_, s ->
                    DropdownMenuItem(text = { Text(text = s) }, onClick = {
                        when (s) {
                            "设置" ->
                                nav.navigate(Destinations.Setting.route)
                            "日志" ->
                                nav.navigate(Destinations.Log.route)
                            "关于" ->
                                nav.navigate(Destinations.About.route)
                            "退出" ->
                                exitProcess(0)
                        }
                        expanded = false
                    })
                }
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            Toast.makeText(appContext, "敬请期待", Toast.LENGTH_SHORT).show()
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { it ->
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            if (!isAccessibilityServiceEnabled(appContext, MyAccessibilityService::class.java.name)) {
                Log.d(tag, "无障碍未开启")
                TextButton(onClick = {
                    requestAccessibilityPermission(appContext)
                }) {
                    Text(text = "点击授予无障碍权限")
                }
            } else if (!isFloatingServiceON(appContext)) {
                Log.d(tag, "无障碍已开启")
                TextButton(onClick = {
                    requestFloatingServicePermission(appContext)
                }) {
                    Text(text = "点击授予悬浮窗权限")
                }
            } else {
                LazyColumn() {
                    items(homeViewModel.fileList) { item ->
                        FileInfoItem(fileInfo = item, onItemClick = { file ->
                            myFloatingWindow(onClick = {
                                Log.d(tag,  "fileName: ${file.name}")
                                var fileContent = FileTool.readFromAssets(appContext,
                                    "scripts/${file.name}")
                                var ret = lua.doString(fileContent)
                                Log.d(tag, "luaResult: $ret")
                            })
                        })
                    }
                }
            }
        }
    }
}