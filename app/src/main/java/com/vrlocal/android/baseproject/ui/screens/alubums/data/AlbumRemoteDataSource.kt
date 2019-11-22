package com.vrlocal.android.baseproject.ui.screens.alubums.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {
    suspend fun getListOfAlbums()
            = getResult { service.getAlbums() }
}
