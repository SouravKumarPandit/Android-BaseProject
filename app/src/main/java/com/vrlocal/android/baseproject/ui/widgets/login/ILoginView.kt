package com.vrlocal.android.baseproject.ui.widgets.login

import com.vrlocal.android.baseproject.ui.base.IView
import com.vrlocal.android.baseproject.ui.widgets.login.data.User

interface ILoginView : IView {

    fun  loginUser(response: User?)
    fun authenticateUser(authenticatedUser: User?)
    fun logoutUser(items: User?)

}