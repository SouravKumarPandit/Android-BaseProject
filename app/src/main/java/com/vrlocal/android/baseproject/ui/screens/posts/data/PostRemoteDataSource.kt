package com.vrlocal.android.baseproject.ui.screens.posts.data

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.PostsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRemoteDataSource @Inject constructor(private val service: PostsService) :
    BaseDataSource() {
    suspend fun getUserPosts() = getResult { service.getPosts() }
}
