package com.vrlocal.android.baseproject.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<T : IView> :ViewModel() {

    var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}
