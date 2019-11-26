package com.vrlocal.android.baseproject.ui.screens.comments.data

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.CommentsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRemoteDataSource @Inject constructor(private val service: CommentsService) : BaseDataSource() {
    suspend fun getUserComments()
            = getResult { service.getComments() }
}
