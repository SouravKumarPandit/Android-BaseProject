package com.vrlocal.android.baseproject.ui.screens.home

import androidx.lifecycle.LiveData
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOption
import com.vrlocal.android.baseproject.ui.screens.login.data.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(  private val repository: HomeRepository) :
    BaseViewModel<IHomeView>() {
    fun getDashBoardOption():LiveData<VResult<List<HomeOption>>> = repository.getHomeOptionList()

}

