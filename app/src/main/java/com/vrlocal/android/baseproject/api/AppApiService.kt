package com.vrlocal.android.baseproject.api

import com.vrlocal.android.baseproject.ui.screens.legoset.data.LegoSet
import com.vrlocal.android.baseproject.ui.screens.legotheme.data.LegoTheme
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Lego REST API access points
 */
interface AppApiService {


    @GET("lego/themes/")
    suspend fun getThemes(@Query("page") page: Int? = null,
                  @Query("page_size") pageSize: Int? = null,
                  @Query("ordering") order: String? = null): Response<ResultsResponse<LegoTheme>>

    @GET("lego/sets/")
    suspend fun getSets(@Query("page") page: Int? = null,
                        @Query("page_size") pageSize: Int? = null,
                        @Query("theme_id") themeId: Int? = null,
                        @Query("ordering") order: String? = null): Response<ResultsResponse<LegoSet>>

    @GET("lego/sets/{id}/")
    suspend fun getSet(@Path("id") id: String): Response<LegoSet>


    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<User>


}
