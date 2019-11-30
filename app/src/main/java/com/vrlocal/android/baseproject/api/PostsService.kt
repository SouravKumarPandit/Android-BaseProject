package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.posts.data.Post
import retrofit2.Response
import retrofit2.http.GET


interface PostsService {


    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>


}
