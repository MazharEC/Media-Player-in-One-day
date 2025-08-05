package com.appsv.mediaplayer.presentation

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.appsv.mediaplayer.presentation.screen.HomeScreen
import com.appsv.mediaplayer.presentation.viewmodel.ViewModel
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun App(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel()
) {
    val storagePermission = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE
    )

    // Request on first launch
    LaunchedEffect(Unit) {
        if (!storagePermission.status.isGranted) {
            storagePermission.launchPermissionRequest()
        }
    }

    if (storagePermission.status.isGranted) {
        HomeScreen(navController = navController)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                storagePermission.launchPermissionRequest()
            }) {
                Text("Grant Storage Permission")
            }
        }
    }
}
