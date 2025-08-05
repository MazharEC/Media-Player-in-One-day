package com.appsv.mediaplayer.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn // ✅ MISSING IMPORT
import androidx.compose.foundation.lazy.items // ✅ MISSING IMPORT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.appsv.mediaplayer.presentation.utils.FolderCard
import com.appsv.mediaplayer.presentation.viewmodel.ViewModel

@Composable
fun FolderScreen(
    navController: NavHostController,
    viewModel: ViewModel = hiltViewModel()
) {
    val videoFolder = viewModel.FolderList.collectAsState().value

    LazyColumn( // ✅ FIXED TYPING ERROR
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(videoFolder.toList()) { (folderName, video) -> // ✅ Syntax is fine if videoFolder is Map
            FolderCard( // ✅ FIXED: Capitalized function name
                folderName = folderName,
                videoCount = video.size,
                navController = navController
            )
        }
    }
}
