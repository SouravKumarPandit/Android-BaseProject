package com.vrlocal.android.baseproject.ui.screens.albums.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.networkLiveData
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class AlbumsRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: AlbumsRemoteDataSource
) {

    fun getListOfAlbums() =
        networkLiveData(networkCall = { remoteSource.getListOfAlbums() }).distinctUntilChanged()

}
