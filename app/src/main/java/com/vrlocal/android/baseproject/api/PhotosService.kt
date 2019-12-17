package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosService {




    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>



    @GET("photos/")
    suspend fun getSets(): Response<List<Photo>>


/*
    @GET("photos/sets/")
    suspend fun getSets(@Query("page") page: Int? = null,
                        @Query("page_size") pageSize: Int? = null,
                        @Query("theme_id") themeId: Int? = null,
                        @Query("ordering") order: String? = null): Response<List<Photo>>
*/


    @GET("photos/{id}/")
    suspend fun getSet(@Path("id") id: String): Response<Photo>

}