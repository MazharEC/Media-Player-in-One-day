package com.appsv.mediaplayer.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsv.mediaplayer.presentation.utils.CustomTopAppBar
import com.appsv.mediaplayer.presentation.utils.VideoCard
import com.appsv.mediaplayer.presentation.viewmodel.ViewModel

@Composable
fun FolderVideosScreen(
    navController: NavHostController,
    foldername: String,
    viewModel: ViewModel = hiltViewModel()
    ) {

    Scaffold(
        topBar = { CustomTopAppBar(topAppBarText = foldername, navController = navController) }
    ){
        innerPadding ->

        val videoFolders = viewModel.FolderList.collectAsState().value
        val videosInFolder = videoFolders[foldername] ?: emptyList()

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            items(videosInFolder){video ->
                VideoCard(
                    path = video.path?: "Untiteled",
                    title = video.title?: "Unknown",
                    size = video.size?: "Unknown",
                    duration = video.duration?: "Unknown",
                    dateAdded = video.dateAdded?: "Unknown",
                    fileName = video.fileName?: "Unknown",
                    thumbnailUrl = video.thumbnailUrl?: "Unknown",
                    id = video.id?: "Unknown",
                    navController = navController
                )
            }
        }
    }
}