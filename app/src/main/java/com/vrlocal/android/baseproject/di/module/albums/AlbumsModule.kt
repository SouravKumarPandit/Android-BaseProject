package com.vrlocal.android.baseproject.di.module.albums

import com.vrlocal.android.baseproject.api.AlbumsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AlbumsModule {


    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAlbumsService(retrofit: Retrofit): AlbumsService {
        return retrofit.create(AlbumsService::class.java)
    }


    /*@JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAlbumsService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AlbumsService::class.java)
*/
}