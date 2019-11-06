package com.vrlocal.android.baseproject.ui.widgets.login

import androidx.lifecycle.LiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.widgets.login.data.LoginRepository
import com.vrlocal.android.baseproject.ui.widgets.login.data.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) :
    BaseViewModel<ILoginView>() {


//    @Inject
//    override lateinit var sessionManager: SessionManager

    lateinit var user: User;

    fun cacheOrNetwork(userId: String): LiveData<VResult<User>> =
        repository.cacheOrNetworkUser(userId)


    fun cacheUser(): LiveData<VResult<User>> =
        repository.authenticateDatabaseUser()

    fun networkUser(userId: String): LiveData<VResult<User>> =
        repository.authenticateNetworkUser(userId)

    fun deleteUser()  = repository.deleteUser()
//    fun getUserList() : Boolean = repository.deleteUser()

}
