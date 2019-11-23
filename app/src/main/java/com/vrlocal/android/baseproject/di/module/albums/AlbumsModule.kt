package com.vrlocal.android.baseproject.di.module.albums

import com.vrlocal.android.baseproject.api.AppApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AlbumsModule {

    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAppApi(retrofit: Retrofit): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }
}