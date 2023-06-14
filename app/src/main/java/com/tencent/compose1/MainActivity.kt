package com.tencent.compose1

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.tencent.compose1.ui.theme.Compose1Theme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import com.tencent.compose1.ui.nav.NavHostApp
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    private val tag:String = "main"

    private fun requestPermissions(permissions: Array<String>, onResult: (List<String>) -> UInt) {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {result ->
            val failed = result.filter {!it.value}.keys
            onResult(failed.toList())
        }.launch(permissions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestList = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


        setContent {
            Compose1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostApp()
                }
            }
        }
    }
}


@SuppressLint("PermissionLaunchedDuringComposition")
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MyApp() {
    val permissionsState = rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val tag = "MyApp"
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
//            openDialog.value = false
        },
            title = {
                Text(text = "警告")
            },
        text = {
            Text(text = "该软件需要申请一些权限")
        },
        confirmButton = {
            TextButton(onClick = {
                if (permissionsState.hasPermission) {
                    Log.d(tag, "有权限")
                    openDialog.value = false
                } else {
                    Log.d(tag, "没有权限")
                    permissionsState.launchPermissionRequest()
                }
//                openDialog.value = false
            }) {
                Text(text = "确认")
            }
        },
        dismissButton = {
            TextButton(onClick = { exitProcess(0) }) {
                Text(text = "退出")
            }
        })
    }


    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Simple TopAppbar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.Check, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Filled.Edit, contentDescription = "Localized description"
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {},
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Localized description")
                        }
                    }
                )
            },
            content = { innerPadding ->
                Column (modifier = Modifier.padding(innerPadding)){
                    PermissionRequired(
                        permissionState = permissionsState,
                        permissionNotGrantedContent = {
                            Log.d(tag, "权限未申请")
                            Text(text = "权限未申请")
                        },
                        permissionNotAvailableContent = {
                            Text(text = "设备不支持")
                            Log.d(tag, "设备不支持")
                        }) {
                        Text(text = "列表信息")
                    }
                }
            }
        )
    }
}