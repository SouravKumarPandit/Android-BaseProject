package com.vrlocal.android.baseproject.ui.base

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import com.vrlocal.android.baseproject.ui.widgets.login.AuthResource
import com.vrlocal.android.baseproject.ui.widgets.login.User

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
@Inject
constructor() {

    // data
    private val cachedUser = MediatorLiveData<AuthResource<User>>()


    val authUser: LiveData<AuthResource<User>>
        get() = cachedUser

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null as User?)
        cachedUser.addSource(source) { userAuthResource ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)

            if (userAuthResource.status == AuthResource.AuthStatus.ERROR) {
                cachedUser.value = AuthResource.logout()
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    companion object {

        private val TAG = "DaggerExample"
    }

}
















