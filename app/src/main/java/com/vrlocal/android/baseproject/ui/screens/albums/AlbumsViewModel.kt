package com.vrlocal.android.baseproject.ui.screens.albums

import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.albums.data.Albums
import com.vrlocal.android.baseproject.ui.screens.albums.data.AlbumsRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
    private var userDao: UserDao,
    private val repository: AlbumsRepository
) : BaseViewModel<IAlbumsView>() {
    lateinit  var albums :Albums

    fun getAlbumList() = repository.getListOfAlbums();

}
