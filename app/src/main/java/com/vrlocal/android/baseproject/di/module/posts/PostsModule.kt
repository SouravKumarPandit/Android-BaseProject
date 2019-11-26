package com.vrlocal.android.baseproject.di.module.posts

import com.vrlocal.android.baseproject.api.PostsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PostsModule {

    @JvmStatic
    @PostActivityScope
    @Provides
    fun providePostsService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }
}