package com.tencent.compose1.entity

import java.util.Date

data class FileInfo(
    val name:String,
    val size: Int,
    val lastTime: Date,
    val suffix:String
)
