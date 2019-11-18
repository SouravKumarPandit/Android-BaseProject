package com.vrlocal.android.baseproject.ui.screens.home

import androidx.lifecycle.LiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.login.data.LoginRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val repository: LoginRepository) : BaseViewModel<ISplashView>() {


    fun getLogeInUserData(): LiveData<VResult<User>> =
        repository.authenticateDatabaseUser()
}