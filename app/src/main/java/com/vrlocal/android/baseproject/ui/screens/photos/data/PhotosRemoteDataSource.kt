package com.vrlocal.android.baseproject.ui.screens.photos.data

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.PhotosService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRemoteDataSource @Inject constructor(private val service: PhotosService) : BaseDataSource() {
    suspend fun getPhotos()
            = getResult {

        val photos = service.getPhotos()


        return@getResult photos

    }
}
