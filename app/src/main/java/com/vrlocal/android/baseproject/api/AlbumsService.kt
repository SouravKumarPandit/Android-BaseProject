package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.albums.data.Albums
import retrofit2.Response
import retrofit2.http.GET


interface AlbumsService {

    @GET("albums")
    suspend fun getAlbums(): Response<Albums>

}
