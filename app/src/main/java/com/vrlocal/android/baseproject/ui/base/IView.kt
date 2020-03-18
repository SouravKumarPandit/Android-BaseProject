package com.vrlocal.android.baseproject.ui.base

import android.view.View
import androidx.annotation.NonNull

interface IView {


    fun showProgressBar()
    fun hideProgressBar()
    fun showError(error:String)
    fun onResponse(responseObject: Any?)
    fun showToast(message: String)
    fun showSnackBar(message: String, statusColor: Int)
    fun onDebouncingClick(@NonNull view: View)


}