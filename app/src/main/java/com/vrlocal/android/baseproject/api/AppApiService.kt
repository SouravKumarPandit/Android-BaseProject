package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.login.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Lego REST API access points
 */
interface AppApiService {



    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<User>
/*
    @GET("posts")
    suspend fun getPosts(): Response<Posts>


    @GET("comments")
    suspend fun getComments(): Response<Comments>


    @GET("albums")
    suspend fun getAlbums(): Response<Albums>*/
}
