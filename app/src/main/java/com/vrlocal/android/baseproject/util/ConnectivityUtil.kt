package com.vrlocal.android.baseproject.util

import com.vrlocal.uicontrolmodule.common.VNetworkUtils

object ConnectivityUtil {

    fun isConnected(): Boolean {
        return  VNetworkUtils.isConnected()

    }
}