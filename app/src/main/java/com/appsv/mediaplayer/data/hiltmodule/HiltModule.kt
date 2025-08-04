package com.appsv.mediaplayer.data.hiltmodule

import com.appsv.mediaplayer.data.repoimpl.VideoFileRepoImple
import com.appsv.mediaplayer.domain.repo.VideoFileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideVideoFileRepo() : VideoFileRepo {

        return VideoFileRepoImple()
    }
}