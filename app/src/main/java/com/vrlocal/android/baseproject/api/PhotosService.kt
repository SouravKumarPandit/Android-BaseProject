package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.photos.data.Photos
import retrofit2.Response
import retrofit2.http.GET

interface PhotosService {


    @GET("photos")
    suspend fun getPhotos(): Response<Photos>


}