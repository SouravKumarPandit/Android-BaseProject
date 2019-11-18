package com.vrlocal.android.baseproject.ui.screens.profile.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.data.databaseLiveData
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: UserProfileRemoteDataSource
) {
    fun getUserData(): LiveData<VResult<User>> = databaseLiveData(
        databaseQuery = {
            dao.getCurrentUser()
        }).distinctUntilChanged()


}