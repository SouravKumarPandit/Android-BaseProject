package com.vrlocal.android.baseproject.di.module

import android.app.Application
import com.google.gson.Gson
import com.vrlocal.android.baseproject.BuildConfig
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.di.CoroutineScropeIO
import com.vrlocal.android.baseproject.util.VConstants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module(includes = [CoreDataModule::class, ActivityBuilderModule::class])
@Module(includes = [ActivityBuilderModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(VConstants.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor(interceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)


    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    /*   @Singleton
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


       @Singleton
       @Provides
       fun providePhotosService(
           okhttpClient: OkHttpClient,
           converterFactory: GsonConverterFactory
       ) = provideService(okhttpClient, converterFactory, PhotosService::class.java)
   */



}
