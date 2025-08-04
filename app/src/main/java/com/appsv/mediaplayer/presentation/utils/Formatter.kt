package com.appsv.mediaplayer.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun FormateDuration(durationInMillis : Long) : String {

    val seconds = (durationInMillis / 1000).toInt()
    val minutes = seconds / 60
    val hours = minutes / 60

    return when{
        hours > 0 -> String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
        minutes > 0 -> String.format("%02d:%02d", minutes, seconds % 60)
        else -> String.format("%02d", seconds)
    }
}

fun FormateFileSize(sizeInBytes : Long) : String{

    val kb = sizeInBytes / 1024.0
    val mb = kb / 1024.0
    val gb = mb / 1024.0

    return when{
        gb >= 1 -> String.format("%.2f GB", gb)
        mb >= 1 -> String.format("%.2f MB", mb)
        kb >= 1 -> String.format("%.2f KB", kb)
        else -> "$sizeInBytes B"
    }
}

fun FormateDate(timestamp : Long) : String{

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp * 1000))
}
