package com.vrlocal.android.baseproject.ui.screens.home

import com.vrlocal.android.baseproject.ui.base.IView
import com.vrlocal.android.baseproject.ui.screens.login.data.User

interface ISplashView : IView {
    fun validateUserSession(data: User?)
}