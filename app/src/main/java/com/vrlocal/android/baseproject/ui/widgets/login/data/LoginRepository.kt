package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.*
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

    fun authenticateDatabaseUser() = databaseLiveData(
        databaseQuery = {
            dao.getCurrentUser()
        }).distinctUntilChanged()


    fun deleteUser() =
        backgroundLiveData(backgroundCallback = { getSumValue() }).distinctUntilChanged()

    private fun getSumValue(): VResult<Boolean> {
        dao.removeUser()
        return VResult.success(true)
    }

}
