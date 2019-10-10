package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.IView

class VResultHandler(resultCallback: IView, result: VResult<Any>){
    init {
        when (result.status) {
            VResult.Status.SUCCESS -> {
                resultCallback.hideProgressBar()
                resultCallback.onResponse(result.data )

            }
            VResult.Status.LOADING -> {
                resultCallback.showProgressBar()

            }
            VResult.Status.ERROR -> {
                resultCallback.hideProgressBar()
                resultCallback.showError(""+result.message)
            }

            VResult.Status.NOT_AUTHENTICATED->{

            }
            VResult.Status.AUTHENTICATED -> {

            }
        }
    }
}
