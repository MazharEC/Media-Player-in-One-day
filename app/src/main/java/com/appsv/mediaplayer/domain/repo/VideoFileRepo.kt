package com.appsv.mediaplayer.domain.repo

import android.app.Application
import com.appsv.mediaplayer.data.model.VideoFile
import kotlinx.coroutines.flow.Flow

interface VideoFileRepo {

    suspend fun getAllVideos(application : Application) : Flow<ArrayList<VideoFile>>
    suspend fun getAllFolders(application : Application) : Flow<Map<String, ArrayList<VideoFile>>>
}