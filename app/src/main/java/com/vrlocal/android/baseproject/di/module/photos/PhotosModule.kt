package com.vrlocal.android.baseproject.di.module.photos

import com.vrlocal.android.baseproject.api.PhotosService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PhotosModule {

    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun providePhotosService(retrofit: Retrofit): PhotosService {
        return retrofit.create(PhotosService::class.java)
    }

}