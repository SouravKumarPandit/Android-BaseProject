package com.vrlocal.android.baseproject.di.module

import android.app.Application
import com.vrlocal.android.baseproject.api.AlbumsService
import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.CommentsService
import com.vrlocal.android.baseproject.api.PostsService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.di.CoroutineScropeIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class, ActivityBuilderModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)


    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideAppApiService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AppApiService::class.java)

    @Singleton
    @Provides
    fun provideCommentService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, CommentsService::class.java)

    @Singleton
    @Provides
    fun providePostsService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, PostsService::class.java)


    @Singleton
    @Provides
    fun provideAlbumsService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AlbumsService::class.java)



}
