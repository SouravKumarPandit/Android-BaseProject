package com.vrlocal.android.baseproject.ui.common.paginationview

import com.vrlocal.android.baseproject.api.BaseDataSource
import com.vrlocal.android.baseproject.api.PhotosService
import javax.inject.Inject

class PageListDataSource @Inject constructor(private val service: PhotosService) :
    BaseDataSource() {


    suspend fun fetchSets(page: Int, pageSize: Int? = null, themeId: Int? = null) = getResult {
        service.getSets()
    }
//            = getResult { service.getSets(page, pageSize, themeId, "-year") }

    suspend fun fetchSet(id: String) = getResult { service.getSet(id) }
}