package com.vrlocal.android.baseproject.ui.widgets.login

import com.vrlocal.android.baseproject.ui.base.IView

interface ILoginView : IView {

    fun loginUser(items: String)
    fun authenticateUser(items: String)
    fun logoutUser(items: String)

}