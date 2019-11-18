package com.vrlocal.android.baseproject.ui.screens.home

import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.login.data.LoginRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject

class HomeViewModel @Inject constructor( private var userDao: UserDao, private val repository: LoginRepository) :
    BaseViewModel<IHomeView>() {



}

