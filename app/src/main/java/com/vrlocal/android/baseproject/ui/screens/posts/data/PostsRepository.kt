package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.networkLiveData
import com.vrlocal.android.baseproject.ui.screens.posts.data.PostRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class PostsRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: PostRemoteDataSource
) {

    fun getListOfPost() =
        networkLiveData(networkCall = { remoteSource.getUserPosts() }).distinctUntilChanged()

}
