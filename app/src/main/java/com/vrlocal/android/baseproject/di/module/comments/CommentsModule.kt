package com.vrlocal.android.baseproject.di.module.comments

import com.vrlocal.android.baseproject.api.CommentsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object CommentsModule {

    @JvmStatic
    @CommentsActivityScope
    @Provides
    fun provideCommentsService(retrofit: Retrofit): CommentsService {
        return retrofit.create(CommentsService::class.java)
    }

}