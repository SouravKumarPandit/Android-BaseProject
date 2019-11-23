package com.vrlocal.android.baseproject.di.module.profile

import com.vrlocal.android.baseproject.api.AppApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ProfileModule {

    @JvmStatic
    @ProfileActivityScope
    @Provides
    fun provideAppApi(retrofit: Retrofit): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }
}