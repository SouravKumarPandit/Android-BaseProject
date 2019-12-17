package com.vrlocal.android.baseproject.ui.screens.login.data

import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosDao
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LegoSetRemoteDataSource  @Inject constructor(themeId: Int?, remoteSource: PhotosRemoteDataSource, dao: PhotosDao, ioCoroutineScope: CoroutineScope) {

}
