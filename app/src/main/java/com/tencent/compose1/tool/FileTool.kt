package com.tencent.compose1.tool

import android.content.Context
import android.net.Uri
import android.util.Log
import com.tencent.compose1.entity.FileInfo
import java.io.File
import java.io.IOException
import java.util.Date

class FileTool {
    companion object{
        private const val tag = "FileTool"
        // by gpt3.5 in 23.6.15

        fun listAssetsFiles(context: Context, path: String): List<FileInfo> {
            val fileList = mutableListOf<FileInfo>()
            val files = context.assets.list(path)
            if (files != null) {
                for (fileName in files) {
                    val fullPath = "$path/$fileName"
                    try {
                        val inputStream = context.assets.open(fullPath)
                        val fileSize = inputStream.available()
                        val uri = Uri.parse("file:///android_asset/$fullPath")
                        val lastModified = Date(File(uri.path).lastModified())
                        val extension = fileName.substringAfterLast('.')
                        Log.d(tag, "File name: $fileName, size: $fileSize bytes," +
                                "last modified: $lastModified extension: $extension")
                        fileList.add(FileInfo(fileName, fileSize, lastModified, extension))
                        inputStream.close()
                    } catch (e: IOException) {
                        Log.e(tag, "Error opening asset $fullPath: " + e.message)
                    }
                    // 递归
                    val subFiles = listAssetsFiles(context, fullPath)
                    fileList.addAll(subFiles)
                }
            } else {
                Log.d(tag, "script 文件夹为空")
            }
            return fileList
        }

        fun readFromAssets(context: Context, fileName: String): String {
            val inputStream = context.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charsets.UTF_8)
        }

    }
}

