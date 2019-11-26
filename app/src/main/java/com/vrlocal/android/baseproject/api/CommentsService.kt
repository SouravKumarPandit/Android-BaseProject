package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.comments.data.Comments
import retrofit2.Response
import retrofit2.http.GET


interface CommentsService {


    @GET("comments")
    suspend fun getComments(): Response<Comments>


}
