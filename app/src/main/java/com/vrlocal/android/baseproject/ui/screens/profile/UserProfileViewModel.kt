package com.vrlocal.android.baseproject.ui.screens.profile

import androidx.lifecycle.LiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.profile.data.ProfileRepository
import javax.inject.Inject

//public class UserProfileViewHolder @Inject constructor(val repository: ProfileRepository) :
//    BaseViewModel<IView>() {}


class UserProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    BaseViewModel<IUserProfileView>() {

    fun  getUserInfo(): LiveData<VResult<User>> = repository.getUserData()
    fun logoutUser() : LiveData<VResult<Boolean>> = repository.deleteUser()

}
