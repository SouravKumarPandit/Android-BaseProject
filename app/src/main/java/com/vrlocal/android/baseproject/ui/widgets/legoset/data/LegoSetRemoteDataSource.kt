package com.vrlocal.android.baseproject.ui.widgets.legoset.data

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoSetRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchSets(page: Int, pageSize: Int? = null, themeId: Int? = null)
            = getResult { service.getSets(page, pageSize, themeId, "-year") }

    suspend fun fetchSet(id: String)
            = getResult { service.getSet(id) }
}
