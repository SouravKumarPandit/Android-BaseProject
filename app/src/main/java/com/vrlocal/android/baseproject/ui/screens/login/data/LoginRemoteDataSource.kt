package com.vrlocal.android.baseproject.ui.screens.login.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject

public class LoginRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {

//    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }
//    suspend fun fetchData() = getResult { service.getUser(5) }

    suspend fun authenticateUser(id: String)
            = getResult { service.getUser(id) }
}
