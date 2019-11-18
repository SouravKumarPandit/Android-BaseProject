package com.vrlocal.android.baseproject.api.session


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.util.VConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject
constructor() {

    // data
    private val cachedUser = MediatorLiveData<VResult<User>>()



    fun authenticateWithId(source: LiveData<VResult<User>>) {
        cachedUser.setValue(VResult.loading(null as User?))

        cachedUser.addSource(source) { vResult ->
            cachedUser.value = vResult
            cachedUser.removeSource(source)

            if (vResult.status == VResult.Status.ERROR) {
                cachedUser.value = VResult.logout()
            }
        }
    }

   /* fun authenticateUser(email: String,password:String){
//        todo
    }*/
//        networkLiveData(networkCall = {});
    fun logOut() {
        Log.d(VConstants.APP_TAG, "logOut: logging out...")
        cachedUser.value = VResult.logout()
    }

    fun getLogeInUser(): LiveData<VResult<User>> {
        return cachedUser
    }


}
















