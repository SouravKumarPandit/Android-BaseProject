package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.widgets.login.User

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>
}
