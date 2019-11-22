package com.vrlocal.android.baseproject.ui.screens.comments.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {
    suspend fun getUserComments()
            = getResult { service.getComments() }
}
