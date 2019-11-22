package com.vrlocal.android.baseproject.api

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun hasNetwork(context: Context): Boolean {
        var isConnected = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) isConnected = true
        return isConnected
    }
}