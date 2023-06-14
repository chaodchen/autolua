package com.tencent.compose1.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tencent.compose1.entity.Todo

@Composable
fun TodoItem(todo: Todo){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = todo.f_name, maxLines = 1, fontSize = 16.sp)
            Text(text = todo.f_size, maxLines = 1, fontSize = 12.sp)
        }
    }
}