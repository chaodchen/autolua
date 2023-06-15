package com.tencent.compose1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tencent.compose1.entity.FileInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme

import com.tencent.compose1.R
import androidx.compose.ui.res.painterResource

@Composable
fun FileInfoItem(fileInfo: FileInfo, onItemClick: (FileInfo)-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_javascript_24),
            contentDescription = "File Icon",
            modifier = Modifier.size(48.dp) // 设置图标大小
        )
        Spacer(modifier = Modifier.width(16.dp)) // 添加间距
        Column {
            Text(
                text = fileInfo.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = fileInfo.size.toString() + " byte",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f)) // 使右侧的图标靠右对齐

        IconButton(onClick = {
            onItemClick(fileInfo)
        }) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Run",
                modifier = Modifier.size(48.dp), // 设置图标大小
            )
        }
    }
}