package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.data.networkLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class LoginRepository @Inject constructor(
//    private val dao: UserDao,
    private val remoteSource: LoginRemoteDataSource
) {
/*

    fun authenticateUser(id: String)= resultLiveData(
        databaseQuery = { dao.getUser() },
        networkCall = { remoteSource.authenticateUser(id) },
        saveCallResult = {dao.insertUser(it) })
        .distinctUntilChanged()
*/

       fun authenticateUser(id: String)= networkLiveData(
        networkCall = {
            remoteSource.authenticateUser(id)
        }).distinctUntilChanged()


}
