package com.vrlocal.android.baseproject.ui.screens.posts.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {
    suspend fun getUserPosts()
            = getResult { service.getPosts() }
}
