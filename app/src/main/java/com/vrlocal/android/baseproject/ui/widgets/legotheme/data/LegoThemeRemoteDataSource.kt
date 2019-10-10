package com.vrlocal.android.baseproject.ui.widgets.legotheme.data

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.BaseDataSource
import javax.inject.Inject

class LegoThemeRemoteDataSource @Inject constructor(private val service: AppApiService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
