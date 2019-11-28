package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.networkLiveData
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class PhotosRepository @Inject constructor(
    private val remoteSource: PhotosRemoteDataSource
) {

    fun getListOfPhotos() =
        networkLiveData(networkCall = {
            remoteSource.getPhotos()
        }).distinctUntilChanged()

}
