package com.tencent.compose1.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.tencent.compose1.ui.components.AccessibilityServiceComponent
import com.tencent.compose1.ui.components.MultiplePermissions

import com.tencent.compose1.ui.components.TodoItem
import com.tencent.compose1.viewmodel.HomeViewModel
import kotlin.system.exitProcess

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(),
               onNavigateToSetting: () -> Unit) {
    
    val tag = "HomeScreen"

    // 进这个页面的时候获取列表数据
    LaunchedEffect(key1 = UInt) {
        homeViewModel.fetchList()

    }

    MultiplePermissions()
    AccessibilityServiceComponent()


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Home")
        })}, floatingActionButton = {
        FloatingActionButton(onClick = {
            onNavigateToSetting()
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(homeViewModel.list) { item ->
                TodoItem(todo = item)

            }
        }
    }
}