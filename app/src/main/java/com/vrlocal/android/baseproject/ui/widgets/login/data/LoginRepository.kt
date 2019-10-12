package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.databaseLiveData
import com.vrlocal.android.baseproject.data.networkLiveData
import com.vrlocal.android.baseproject.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class LoginRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: LoginRemoteDataSource
) {

    fun cacheOrNetworkUser(id: String) = resultLiveData(
        databaseQuery = { dao.getCurrentUser() },
        networkCall = { remoteSource.authenticateUser(id) },
        saveCallResult = { dao.upsert(it) })
        .distinctUntilChanged()

    fun authenticateNetworkUser(id: String) = networkLiveData(
        networkCall = {
            remoteSource.authenticateUser(id)
        }).distinctUntilChanged()

    fun authenticateDatabaseUser(id: String) = databaseLiveData(
        databaseQuery = {
            dao.getCurrentUser()
        }).distinctUntilChanged()
}
