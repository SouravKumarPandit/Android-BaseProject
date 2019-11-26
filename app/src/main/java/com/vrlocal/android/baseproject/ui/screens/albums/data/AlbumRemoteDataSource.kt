package com.vrlocal.android.baseproject.ui.screens.albums.data

import com.vrlocal.android.baseproject.api.AlbumsService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRemoteDataSource @Inject constructor(private val service: AlbumsService) : BaseDataSource() {
    suspend fun getListOfAlbums()
            = getResult { service.getAlbums() }
}
