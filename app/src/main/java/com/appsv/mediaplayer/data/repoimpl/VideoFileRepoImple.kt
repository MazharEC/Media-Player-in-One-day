package com.appsv.mediaplayer.data.repoimpl

import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import com.appsv.mediaplayer.data.model.VideoFile
import com.appsv.mediaplayer.domain.repo.VideoFileRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

class VideoFileRepoImple : VideoFileRepo {
    override suspend fun getAllVideos(application: Application): Flow<ArrayList<VideoFile>> {

        val allVideo = ArrayList<VideoFile>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DISPLAY_NAME

        )
        val  Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val memoryCursor = application.contentResolver.query(Uri, projection, null, null, null)

        if (memoryCursor != null) {
            while (memoryCursor.moveToNext()) {

                val id = memoryCursor.getString(0)
                val path = memoryCursor.getString(1)
                val title = memoryCursor.getString(2)
                val size = memoryCursor.getString(3)
                val duration = memoryCursor.getString(4)
                val dateAdded = memoryCursor.getString(5)
                val fileName = memoryCursor.getString(6)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toLong()
                )

                val videoFile = VideoFile(
                    id = id,
                    path = path,
                    title = title,
                    fileName = fileName,
                    size = size,
                    duration = duration,
                    dateAdded = dateAdded,
                    thumbnailUrl = contentUri.toString()
                )
                allVideo.add(videoFile)
                if(memoryCursor.isLast){
                    break
                }
            }
            memoryCursor.close()
        }
        return flow{
            emit(allVideo)
        }
    }

    override suspend fun getAllFolders(application: Application): Flow<Map<String, ArrayList<VideoFile>>> {
        val allVideos = getAllVideos(application).first()
        val videosByFolders = allVideos
            .groupBy { File(it.path).parent ?: "Unknown Folder" }
            .mapValues { ArrayList(it.value) } // ✅ Fix: Convert List to ArrayList

        return flow {
            emit(videosByFolders)
        }
    }

}