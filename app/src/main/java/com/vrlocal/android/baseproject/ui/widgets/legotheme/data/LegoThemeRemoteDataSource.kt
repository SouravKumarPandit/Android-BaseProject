package com.vrlocal.android.baseproject.ui.widgets.legotheme.data

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoThemeRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
