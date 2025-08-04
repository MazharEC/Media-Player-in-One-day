package com.appsv.mediaplayer.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.mediaplayer.data.model.VideoFile
import com.appsv.mediaplayer.domain.repo.VideoFileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor( val repo : VideoFileRepo, val application : Application): ViewModel(){

    val showUi = MutableStateFlow(false)
    val videoList = MutableStateFlow(emptyList<VideoFile>())
    val FolderList = MutableStateFlow(emptyMap<String, ArrayList<VideoFile>>())

    var isLoading = MutableStateFlow(false)

    fun LoadVideoFiles(){
        isLoading.value = true
        viewModelScope.launch{
            repo.getAllVideos(application).collectLatest{
                videoList.value = it

            }
        }
        isLoading.value = false

    }

    fun LoadVideofiles(){
        isLoading.value = true

        viewModelScope.launch{
            repo.getAllFolders(application).collectLatest{
                FolderList.value = it
            }
        }
        isLoading.value = false

    }
    init {
        viewModelScope.launch {
            LoadVideoFiles()
            LoadVideofiles()
        }
    }
}