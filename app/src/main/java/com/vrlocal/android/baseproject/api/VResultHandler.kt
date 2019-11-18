package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.IView

open class VResultHandler(resultCallback: IView, result: VResult<Any>, callerFun: (Any) -> Unit) {
    init {
        when (result.status) {

            VResult.Status.LOADING -> {
                resultCallback.showProgressBar()

            }
            VResult.Status.ERROR -> {
                resultCallback.hideProgressBar()
                resultCallback.showError("" + result.message)
            }
            VResult.Status.SUCCESS -> {
                resultCallback.hideProgressBar()
                if (result.data != null) {
                    callerFun.invoke(result.data)
//                    resultCallback.onResponse(result.data)
                }
            }
            VResult.Status.NOT_AUTHENTICATED -> {

            }
            VResult.Status.AUTHENTICATED -> {

            }

        }
    }
}
