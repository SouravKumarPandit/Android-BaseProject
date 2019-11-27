package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.common.paginationview.PageList
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotosService {



    @GET("photos")
    suspend fun getPagePhotos(pageIndex:Int): Response<PageList>

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>


}