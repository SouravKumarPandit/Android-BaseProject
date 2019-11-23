package com.vrlocal.android.baseproject.ui.screens.login

import com.vrlocal.android.baseproject.ui.base.IView
import com.vrlocal.android.baseproject.ui.screens.login.data.User

interface ILoginView : IView {

    fun isValidUser(authenticatedUser: User?)
    fun validateUserSession(data: User?)
}