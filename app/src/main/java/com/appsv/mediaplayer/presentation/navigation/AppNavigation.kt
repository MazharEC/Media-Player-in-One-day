package com.appsv.mediaplayer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.appsv.mediaplayer.presentation.App
import com.appsv.mediaplayer.presentation.screen.FolderScreen
import com.appsv.mediaplayer.presentation.screen.FolderVideosScreen
import com.appsv.mediaplayer.presentation.screen.HomeScreen
import com.appsv.mediaplayer.presentation.screen.VideoPlayerScreen

@Composable
fun  AppNavigation (){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItems.App){

        composable<NavigationItems.App>{
            App(navController = navController)
        }

        composable<NavigationItems.HomeScreen>{
            HomeScreen(navController)

        }

        composable<NavigationItems.PlayerScreen>{BackStackEntry ->
            val url : NavigationItems.PlayerScreen = BackStackEntry.toRoute()
            VideoPlayerScreen(url.VideoUrl, navController = navController)
        }

        composable<NavigationItems.AllVideoFolder>{
            val folderName : NavigationItems.AllVideoFolder = it.toRoute()
            FolderScreen(navController = navController)
        }

        composable<NavigationItems.FolderVideosScreen>{
            val folderName : NavigationItems.FolderVideosScreen = it.toRoute()
            FolderVideosScreen(navController = navController, foldername = folderName.folderName)
        }

    }

}