package com.vrlocal.android.baseproject.ui.screens.login

import androidx.lifecycle.LiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.login.data.LoginRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) :
    BaseViewModel<ILoginView>() {


//    @Inject
//    override lateinit var sessionManager: SessionManager

    lateinit var user: User

    fun authenticateUser(userId: String): LiveData<VResult<User>> =
        repository.cacheOrNetworkUser(userId)


    fun cacheUser(): LiveData<VResult<User>> =
        repository.authenticateDatabaseUser()

    fun networkUser(userId: String): LiveData<VResult<User>> =
        repository.authenticateNetworkUser(userId)

    fun deleteUser()  = repository.deleteUser()
//    fun getUserList() : Boolean = repository.deleteUser()

//    ---------------------------------------splash screen------------------------------------------------

    fun getLogeInUserData(): LiveData<VResult<User>> =
        repository.authenticateDatabaseUser()



}
