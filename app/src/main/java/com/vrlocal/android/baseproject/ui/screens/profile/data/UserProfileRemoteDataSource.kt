package com.vrlocal.android.baseproject.ui.screens.profile.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserProfileRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {

    suspend fun authenticateUser(id: String)
            = getResult { service.getUser(id) }
}