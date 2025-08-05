package com.appsv.mediaplayer

import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.appsv.mediaplayer.presentation.navigation.AppNavigation
import com.appsv.mediaplayer.presentation.screen.SplashScreen
import com.appsv.mediaplayer.ui.theme.MediaPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(

){

    val showSplash = remember { mutableStateOf(true) }
    LaunchedEffect(Unit){

        android.os.Handler(Looper.getMainLooper()).postDelayed(
            { showSplash.value = false },
            2000
        )
    }

    if(showSplash.value){
        SplashScreen()
    }else{
        AppNavigation()
    }
}



