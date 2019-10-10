package com.vrlocal.android.baseproject.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<T : IView> :ViewModel(){
//    override fun showProgressBar() {
//        view?.showProgressBar();
//    }
//
//    override fun hideProgressBar() {
//        view?.hideProgressBar();
//
//    }
//
//    override fun showError(error: String) {
//        view?.showError(error);
//
//    }
//
//    override fun onResponse(reposeObject: Any) {
//        view?.onResponse(reposeObject)
//    }
//
//    override fun showToast(message: String) {
//        view?.showToast(message);
//
//    }
//
//    override fun showSnackBar(message: String, statusColor: Int) {
//        view?.showSnackBar(message,statusColor)
//    }

    var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}
