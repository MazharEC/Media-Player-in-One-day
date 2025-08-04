package com.appsv.mediaplayer.presentation.navigation
import kotlinx.serialization.Serializable

sealed class NavigationItems {

    @Serializable
    object App

    @Serializable
    object HomeScreen

    @Serializable
    data class PlayerScreen(val VideoUrl : String, val title :String? = null)

    @Serializable
    data class FolderVideosScreen(val folderName : String)

    @Serializable
    data class AllVideoFolder(val folderName : String)
}